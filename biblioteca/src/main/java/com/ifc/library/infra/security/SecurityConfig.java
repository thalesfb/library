package com.ifc.library.infra.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    SecurityFilter securityFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                                .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
                                .requestMatchers(HttpMethod.POST, "/auth/register").permitAll()
                                .requestMatchers(HttpMethod.DELETE, "/user/**").authenticated()
                                .requestMatchers(HttpMethod.POST, "/library/book").authenticated()
                                .requestMatchers(HttpMethod.GET, "/library/book").permitAll()
                                .requestMatchers(HttpMethod.DELETE, "/library/book/**").authenticated()
                                .requestMatchers(HttpMethod.POST, "/library/loan").authenticated()
                                .requestMatchers(HttpMethod.GET, "/library/loan").permitAll()
                                .requestMatchers(HttpMethod.DELETE, "/library/loan/**").authenticated()
                                .requestMatchers(HttpMethod.POST, "/library/author").authenticated()
                                .requestMatchers(HttpMethod.GET, "/library/author").permitAll()
                                .requestMatchers(HttpMethod.DELETE, "/library/author/**").authenticated()
                                .anyRequest().authenticated()
                )
                
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}