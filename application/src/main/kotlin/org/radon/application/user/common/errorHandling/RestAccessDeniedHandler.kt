package org.radon.application.user.common.errorHandling

import jakarta.servlet.ServletException
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.access.AccessDeniedException
import org.springframework.security.web.access.AccessDeniedHandler
import org.springframework.stereotype.Component
import tools.jackson.databind.ObjectMapper
import java.io.IOException
import java.time.Instant


@Component
class RestAccessDeniedHandler : AccessDeniedHandler {
    private val mapper: ObjectMapper = ObjectMapper()

    @Throws(IOException::class, ServletException::class)
    override fun handle(
        request: HttpServletRequest,
        response: HttpServletResponse,
        accessDeniedException: AccessDeniedException
    ) {
        response.setStatus(HttpServletResponse.SC_FORBIDDEN)
        response.setContentType("application/json")
        response.setCharacterEncoding("UTF-8")

        val body: MutableMap<String?, Any?> = LinkedHashMap<String?, Any?>()

        body.put("timestamp", Instant.now().toString())
        body.put("status", HttpServletResponse.SC_FORBIDDEN)
        body.put("error", "Forbidden")
        body.put("message", "You are not authorized to access this resource!")
        body.put("path", request.getServletPath())

        mapper.writeValue(response.getOutputStream(), body)
    }
}
