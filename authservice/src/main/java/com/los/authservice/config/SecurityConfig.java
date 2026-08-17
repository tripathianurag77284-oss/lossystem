package com.los.authservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.SecurityFilterChain;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private static final String SECRET =
            "my-secret-key-for-los-platform-12345678901234567890";

    @Bean
    public SecretKey secretKey() {

        return new SecretKeySpec(
                SECRET.getBytes(StandardCharsets.UTF_8),
                "HmacSHA256"
        );
    }

    @Bean
    public JwtDecoder jwtDecoder(
            SecretKey secretKey) {

        return NimbusJwtDecoder
                .withSecretKey(secretKey)
                .build();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http) throws Exception {

        http

                .csrf(csrf ->
                        csrf.disable()
                )

                .authorizeHttpRequests(auth -> auth

                        // Login doesn't need JWT
                        .requestMatchers(
                                "/api/auth/login"
                        ).permitAll()

                        // Actuator health
                        .requestMatchers(
                                "/actuator/health"
                        ).permitAll()

                        // Swagger UI
                        .requestMatchers(
                                "/swagger-ui/**"
                        ).permitAll()

                        .requestMatchers(
                                "/swagger-ui.html"
                        ).permitAll()

                        // OpenAPI documentation
                        .requestMatchers(
                                "/v3/api-docs/**"
                        ).permitAll()

                        // Everything else requires JWT
                        .anyRequest()
                        .authenticated()
                )

                .oauth2ResourceServer(
                        oauth2 ->
                                oauth2.jwt(
                                        Customizer.withDefaults()
                                )
                );

        return http.build();
    }
}