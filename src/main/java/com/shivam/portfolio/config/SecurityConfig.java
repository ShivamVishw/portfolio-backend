package com.shivam.portfolio.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.shivam.portfolio.security.JwtFilter;

@Configuration
public class SecurityConfig {

    private final JwtFilter jwtFilter;

    public SecurityConfig(JwtFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http) throws Exception {

        http
            .csrf(csrf -> csrf.disable())
    
            .cors(cors -> {})

            .authorizeHttpRequests(auth -> auth

                .requestMatchers(
                		"/api/auth/**",
                	    "/api/contact"
//                        "/api/auth/**",
//                        "/api/contact",
//                        "/api/contact/**"
                ).permitAll()

                .anyRequest().authenticated()
            )

            .addFilterBefore(
                    jwtFilter,
                    UsernamePasswordAuthenticationFilter.class
            );

        return http.build();
    }
}