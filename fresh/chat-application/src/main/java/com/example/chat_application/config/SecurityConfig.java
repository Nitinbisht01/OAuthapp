package com.example.chat_application.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.chat_application.security.JwtAuthFilter;
import com.example.chat_application.security.OAuthSuccessHandler;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthFilter jwtAuthFilter;
    private final OAuthSuccessHandler oAuthSuccessHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
            .csrf(csrf -> csrf.disable())

            // ✅ CORS (important for frontend)
            .cors(cors -> {})

            // ✅ Stateless (JWT)
            .sessionManagement(session ->
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )

            // ✅ URL permissions
            .authorizeHttpRequests(auth -> auth

                // 🔥 PUBLIC endpoints
                .requestMatchers(
                        "/api/v1/auth/**",
                        "/oauth2/**",
                        "/login/**"
                ).permitAll()

                // 🔐 protected
                .anyRequest().authenticated()
            )

            // 🔥 OAuth2 Login
            .oauth2Login(oauth -> oauth
                .successHandler(oAuthSuccessHandler)
            );

        // 🔥 JWT filter
        http.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    // ✅ Authentication Manager (needed for login)
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}