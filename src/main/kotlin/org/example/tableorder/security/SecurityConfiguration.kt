package org.example.tableorder.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
class SecurityConfiguration(private val authenticationFilter: JwtAuthenticationFilter){

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .httpBasic { it.disable() } // HTTP Basic 비활성화
            .csrf { it.disable() } // CSRF 비활성화
            .sessionManagement { it.sessionCreationPolicy(SessionCreationPolicy.STATELESS) } // JWT 사용을 위한 세션 비활성화
            .authorizeHttpRequests { auth ->
                auth
                    .requestMatchers("/auth/signup", "/auth/signin", "/user/stores").permitAll() // 회원가입 및 로그인 경로 허용
                    .anyRequest().authenticated() // 그 외의 모든 요청은 인증 필요
            }
            .addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter::class.java) // JWT 필터 추가

        return http.build()
    }

    @Bean
    fun authenticationManager(authenticationConfiguration: AuthenticationConfiguration): AuthenticationManager {
        return authenticationConfiguration.authenticationManager
    }
}
