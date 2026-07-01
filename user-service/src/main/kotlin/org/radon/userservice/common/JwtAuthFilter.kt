package org.radon.userservice.common

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.radon.cargoamigo.common.ErrorResponse
import org.radon.cargoamigo.common.Response
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import tools.jackson.databind.ObjectMapper

@Component
class JwtAuthFilter(
    private val jwtUtil: JWTUtil,
) : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        runCatching {
            val authHeader = request.getHeader(HttpHeaders.AUTHORIZATION)
            if (authHeader != null && authHeader.startsWith("Bearer ")) {
                val token = authHeader.substring(7)
                if (SecurityContextHolder.getContext().authentication == null) {
                    val userDetails = jwtUtil.extractUser(token)
                    if (jwtUtil.isTokenValid(token, userDetails)) {
                        val authToken = UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities())
                        authToken.details = WebAuthenticationDetailsSource().buildDetails(request)
                        SecurityContextHolder.getContext().authentication = authToken
                    }
                }
            }
        }.fold(
            onSuccess = {
                filterChain.doFilter(request, response)
            },
            onFailure = { ex ->
                response.status = HttpStatus.UNAUTHORIZED.value()
                response.contentType = "application/json"

                val body = Response(
                    data = null,
                    error = ErrorResponse(
                        status = HttpStatus.UNAUTHORIZED.value(),
                        error = "Unauthorized",
                        path = request.requestURI,
                        message = ex.message.toString()
                    )
                )

                ObjectMapper().writeValue(response.outputStream, body)
            }
        )


    }

}