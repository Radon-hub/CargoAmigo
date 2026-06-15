package org.radon.cargoamigo.auth.common

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component
import java.util.Date
import kotlin.time.Instant

@Component
class JWTUtil {

    @Value($$"${app.security.jwt.SECRET_KEY}")
    private val SECRET_KEY: String = ""
    @Value($$"${app.security.jwt.ACCESS_TOKEN_VALIDITY_SECONDS}")
    private val ACCESS_TOKEN_VALIDITY_SECONDS: Long = 0
    @Value($$"${app.security.jwt.REFRESH_TOKEN_VALIDITY_SECONDS}")
    private val REFRESH_TOKEN_VALIDITY_SECONDS: Long = 0

    fun tokenGenerator(userDetails: UserDetails,expiration: Long): String = Jwts.builder()
        .subject(userDetails.username)
        .claim("authorities", userDetails.authorities)
        .issuedAt(Date())
        .expiration(Date(expiration))
        .signWith(Keys.hmacShaKeyFor(SECRET_KEY.toByteArray()))
        .compact()

    fun generateAccessToken(userDetails: UserDetails): String =
            tokenGenerator(userDetails,System.currentTimeMillis() + ACCESS_TOKEN_VALIDITY_SECONDS)


    fun generateRefreshToken(userDetails: UserDetails): String =
            tokenGenerator(userDetails,System.currentTimeMillis() + REFRESH_TOKEN_VALIDITY_SECONDS)

    fun





}

