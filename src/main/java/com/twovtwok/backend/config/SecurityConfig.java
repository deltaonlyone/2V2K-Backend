package com.twovtwok.backend.config;

import com.twovtwok.backend.filter.AuthTokenFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

import static org.springframework.http.HttpMethod.POST;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig{

    private final AuthTokenFilter authTokenFilter;

    @Bean
    public SecurityFilterChain securityFilter(HttpSecurity http) throws Exception {
        return http
                .cors().and()
                // disable CSRF as it's not needed for stateless applications with token based authentication
                .csrf().disable()
                // stateless (so also disable logout)
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeHttpRequests((authorize) ->
                        authorize
                                .requestMatchers(POST, "/api/auth/authenticate").anonymous()
//                                .requestMatchers(POST, "/api/**").anonymous()
                                .requestMatchers( "/api/users/**").authenticated()
                                .requestMatchers( "/api/photos/**").authenticated()
                ).addFilterBefore(authTokenFilter, BasicAuthenticationFilter.class)
                .build();
    }

    private UrlBasedCorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration conf = new CorsConfiguration();
        conf.setAllowedOrigins(List.of("http://localhost:3000/"));
        conf.setAllowedMethods(List.of("*"));
        conf.setAllowedHeaders(List.of("*"));
        conf.setAllowCredentials(true); // Cookies
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", conf);
        return source;
    }

}
