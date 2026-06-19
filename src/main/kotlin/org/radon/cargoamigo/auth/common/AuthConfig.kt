package org.radon.cargoamigo.auth.common

import org.radon.cargoamigo.auth.application.port.out.UserRepository
import org.radon.cargoamigo.common.errorHandling.RestAccessDeniedHandler
import org.radon.cargoamigo.common.errorHandling.RestAuthenticationEntryPoint
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.annotation.Order
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.ProviderManager
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer
import org.springframework.security.config.annotation.web.configurers.SessionManagementConfigurer
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
@EnableWebSecurity
class AuthConfig(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
    private val jwtAuthFilter: JwtAuthFilter,
    private val restAuthenticationEntryPoint: RestAuthenticationEntryPoint,
    private val restAccessDeniedHandler: RestAccessDeniedHandler,
) {

    @Bean
    fun authenticationProvider(): AuthenticationProvider {
        val provider = DaoAuthenticationProvider(userRepository)
        provider.setPasswordEncoder(passwordEncoder)
        return provider
    }

    @Bean
    @Throws(Exception::class)
    fun authenticationManager(): AuthenticationManager {
        return ProviderManager(authenticationProvider())
    }

    @Bean
    @Order(1)
    @Throws(Exception::class)
    fun publicChain(http: HttpSecurity): SecurityFilterChain? {
        http
            .csrf{ it.disable() }
            .securityMatcher("/public/**", "/public", "/auth/**", "/auth")
            .authorizeHttpRequests(Customizer { auth ->
                auth
                    .anyRequest()
                    .permitAll()
                }
            )
        return http.build()
    }

    @Bean
    @Order(2)
    @Throws(Exception::class)
    fun apiChain(http: HttpSecurity): SecurityFilterChain? {
        http
            .csrf{ it.disable() }
            .securityMatcher("/api/**")
            .sessionManagement(Customizer { session: SessionManagementConfigurer<HttpSecurity?>? ->
                session!!.sessionCreationPolicy(
                    SessionCreationPolicy.STATELESS
                )
            })
            .exceptionHandling(Customizer { ex ->
                ex
                    .authenticationEntryPoint(restAuthenticationEntryPoint)
                    .accessDeniedHandler(restAccessDeniedHandler)
                }
            )
            .authorizeHttpRequests(Customizer { auth ->
                auth
                    .anyRequest()
                    .authenticated()
                }
            )
            .authenticationProvider(authenticationProvider())
            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter::class.java)
        return http.build()
    }

}