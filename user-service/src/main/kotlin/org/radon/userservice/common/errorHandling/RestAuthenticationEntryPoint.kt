package org.radon.userservice.common.errorHandling

import jakarta.servlet.ServletException
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.stereotype.Component
import tools.jackson.databind.ObjectMapper
import java.io.IOException
import java.time.Instant


@Component
class RestAuthenticationEntryPoint : AuthenticationEntryPoint {
    private val mapper: ObjectMapper = ObjectMapper()


    @Throws(IOException::class, ServletException::class)
    override fun commence(
        request: HttpServletRequest,
        response: HttpServletResponse,
        authException: AuthenticationException
    ) {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED)
        response.setContentType("application/json")
        response.setCharacterEncoding("UTF-8")

        val body: MutableMap<String?, Any?> = LinkedHashMap<String?, Any?>()

        body.put("timestamp", Instant.now().toString())
        body.put("status", HttpServletResponse.SC_UNAUTHORIZED)
        body.put("error", "Unauthorized")
        body.put("message", "Authentication required (Your user may not be authenticated or Token expired!)")
        body.put("path", request.getServletPath())

        mapper.writeValue(response.getOutputStream(), body)
    }
}
