package org.radon.application.user.application.service

import org.radon.application.user.application.port.`in`.LoginUseCase
import org.radon.application.user.application.port.`in`.RefreshTokenUseCase
import org.radon.application.user.application.port.`in`.SignupUseCase
import org.radon.application.user.presentation.dto.LoginRequest
import org.radon.application.user.presentation.dto.RefreshTokenRequest
import org.radon.application.user.presentation.dto.SignupRequest
import org.radon.application.user.application.port.out.UserRepository
import org.radon.application.user.common.JWTUtil
import org.radon.application.user.domain.Tokens
import org.radon.application.user.domain.User
import org.radon.cargoamigo.common.TokenType
import org.radon.cargoamigo.common.exceptionHandling.ApplicationException
import org.radon.cargoamigo.common.exceptionHandling.DataBaseException
import org.radon.cargoamigo.common.exceptionHandling.RequestMustNotBeNullException
import org.radon.application.user.common.exceptionHandling.TokenNotValidException
import org.radon.application.user.common.exceptionHandling.WrongPasswordException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class AuthService(
    private val repository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
    private val jwtUtil: JWTUtil,
) : LoginUseCase, SignupUseCase, RefreshTokenUseCase {

    override fun login(request: LoginRequest?): Tokens {

        requireNotNull(request) {
            throw RequestMustNotBeNullException()
        }

        val user = repository.loadUserByUsername(username = request.username)

        if(!passwordEncoder.matches(request.password, user.password)) {
            throw WrongPasswordException()
        }


        val accessToken: String = jwtUtil.generateAccessToken(user)
        val refreshToken: String = jwtUtil.generateRefreshToken(user)


        return Tokens(
            accessToken = accessToken,
            refreshToken = refreshToken,
        )

    }

    override fun signup(request: SignupRequest?): Tokens {

        requireNotNull(request) {
            throw RequestMustNotBeNullException()
        }

        request.validateRequest()

        val userCreated = repository.createUser(
            User (
                firstName = request.firstname,
                lastName = request.lastname,
                phoneNumber = request.phone,
                type = request.type,
                age = request.age,
                passwordHash = request.password
            )
        ) ?: throw DataBaseException("Could not create user!")



        val accessToken: String = jwtUtil.generateAccessToken(userCreated)
        val refreshToken: String = jwtUtil.generateRefreshToken(userCreated)


        return Tokens(
            accessToken = accessToken,
            refreshToken = refreshToken,
        )

    }

    override fun refreshToken(request: RefreshTokenRequest?): Tokens {

        requireNotNull(request) {
            throw RequestMustNotBeNullException()
        }

        var username = ""

        runCatching {
            jwtUtil.extractUserName(request.refreshToken)
        }.fold(
            onSuccess = { username = it },
            onFailure = { throw ApplicationException(it.message ?: "Something went wrong!") }
        )

        runCatching{
            jwtUtil.extractTokenType(request.refreshToken)
        }.fold(
            onSuccess = { tokenType -> if(tokenType == TokenType.ACCESS_TOKEN_JWTs) throw TokenNotValidException()},
            onFailure = { throw ApplicationException("Something went wrong!") }
        )

        val user = repository.loadUserByUsername(username = username)

        runCatching {
            jwtUtil.isTokenValid(request.refreshToken, user)
        }.getOrElse{ throw ApplicationException(it.message ?: "Something went wrong!") }

        val accessToken: String = jwtUtil.generateAccessToken(user)
        val refreshToken: String = jwtUtil.generateRefreshToken(user)

        return Tokens(
            accessToken = accessToken,
            refreshToken = refreshToken,
        )
    }
}