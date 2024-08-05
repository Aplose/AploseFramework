/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aplose.aploseframework.config;

import com.aplose.aploseframework.model.RoleEnum;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 *
 * @author oandrade
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }    
    @Bean
    @Order(1)
    SecurityFilterChain apiSecurityFilterChain(HttpSecurity http) throws Exception {
        return http
            .securityMatcher("/api/**")
            .authorizeHttpRequests(auth -> {
                auth.requestMatchers(HttpMethod.GET, "/api/ping","/api/config","/api/category","/api/dictionnary").permitAll();
                auth.anyRequest().authenticated();
            })
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .httpBasic(Customizer.withDefaults())
            .build();
    }
    @Bean
    @Order(2)
    SecurityFilterChain h2ConsoleSecurityFilterChain(HttpSecurity http) throws Exception {
        return http.securityMatcher(AntPathRequestMatcher.antMatcher("/h2-console/**"))
            .authorizeHttpRequests( auth -> {
                auth.requestMatchers(AntPathRequestMatcher.antMatcher("/h2-console/**")).permitAll();
            })
            .csrf(csrf -> csrf.ignoringRequestMatchers(AntPathRequestMatcher.antMatcher("/h2-console/**")))
            .headers(headers -> headers.frameOptions(frameOptions -> frameOptions.disable()))
            .build();
    }  

    @Bean
    @Order(3)
    SecurityFilterChain configureSecurityAF(HttpSecurity http) throws Exception {
      http.authorizeHttpRequests(request -> request 
        .requestMatchers("/login").permitAll()
        .requestMatchers("/logout").authenticated()
        .anyRequest().denyAll())
        .httpBasic(Customizer.withDefaults());
        return http.build();
    }
}
