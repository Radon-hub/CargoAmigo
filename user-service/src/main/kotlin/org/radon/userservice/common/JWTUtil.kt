package org.radon.userservice.common

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.radon.userservice.domain.Authority
import org.radon.userservice.domain.User
import org.radon.cargoamigo.common.TokenType
import org.radon.userservice.common.exceptionHandling.TokenNotValidException
import org.radon.userservice.domain.Role
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component
import java.util.*
import kotlin.collections.get

@Component
class JWTUtil {

    @Value($$"${app.security.jwt.SECRET_KEY}")
    private val SECRET_KEY: String = ""
    @Value($$"${app.security.jwt.ACCESS_TOKEN_VALIDITY_SECONDS}")
    private val ACCESS_TOKEN_VALIDITY_SECONDS: Long = 0
    @Value($$"${app.security.jwt.REFRESH_TOKEN_VALIDITY_SECONDS}")
    private val REFRESH_TOKEN_VALIDITY_SECONDS: Long = 0

    private fun tokenGenerator(userDetails: UserDetails,expiration: Long,tokenType: TokenType): String = Jwts.builder()
        .subject(userDetails.username)
        .claim("authorities", userDetails.authorities)
        .claim("tokenType", tokenType)
        .issuedAt(Date())
        .expiration(Date(expiration))
        .signWith(Keys.hmacShaKeyFor(SECRET_KEY.toByteArray()))
        .compact()

    fun generateAccessToken(userDetails: UserDetails): String =
            tokenGenerator(userDetails,System.currentTimeMillis() + ACCESS_TOKEN_VALIDITY_SECONDS,TokenType.ACCESS_TOKEN_JWTs)


    fun generateRefreshToken(userDetails: UserDetails): String =
            tokenGenerator(userDetails,System.currentTimeMillis() + REFRESH_TOKEN_VALIDITY_SECONDS,TokenType.REFRESH_TOKEN_JWTs)

    fun extractUserName(token: String): String =
        Jwts.parser()
            .verifyWith(Keys.hmacShaKeyFor(SECRET_KEY.toByteArray()))
            .build()
            .parseSignedClaims(token)
            .payload
            .subject

    fun isExpired(token: String): Boolean {
        val date = Jwts.parser()
            .verifyWith(Keys.hmacShaKeyFor(SECRET_KEY.toByteArray()))
            .build()
            .parseSignedClaims(token)
            .payload
            .expiration
        return date.before(Date())
    }

    fun extractTokenType(token:String): TokenType{
        val body = Jwts.parser()
            .verifyWith(Keys.hmacShaKeyFor(SECRET_KEY.toByteArray()))
            .build()
            .parseSignedClaims(token)
            .payload ?: throw TokenNotValidException()

        return TokenType.valueOf(body["tokenType"].toString())
    }

    fun isTokenValid(token:String,userDetails: UserDetails): Boolean {
        val userName = extractUserName(token)
        return userName == userDetails.username && !isExpired(token)
    }

    fun extractUser(token: String?): User {
        val body = Jwts.parser()
            .verifyWith(Keys.hmacShaKeyFor(SECRET_KEY.toByteArray()))
            .build()
            .parseSignedClaims(token)
            .payload ?: throw TokenNotValidException()

        val authoritiesClaim = body["authorities"]

        val authorities = (authoritiesClaim as? List<*>)
            ?.filterIsInstance<Map<*, *>>()
            ?.map { map ->
                Authority(
                    map["authority"]?.toString()
                        ?: throw IllegalArgumentException("Authority value missing")
                )
            }
            ?: emptyList()

        return User(
            phoneNumber = body.subject,
            role = Role(authorities = authorities.toSet()),
        )
    }
}

