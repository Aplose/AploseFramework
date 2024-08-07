/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aplose.aploseframework.config;

import com.aplose.aploseframework.ZDEVELOP.developHelper;
import com.aplose.aploseframework.filter.JwtAuthFilter;
import com.aplose.aploseframework.model.RoleEnum;
import com.aplose.aploseframework.repository.UserAccountRepository;
import com.aplose.aploseframework.security.DolibarrAuthenticationProvier;
import com.aplose.aploseframework.service.UserAccountService;
import com.aplose.aploseframework.service.UserDetailsServiceImpl;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 *
 * @author oandrade
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {



    @Bean
    public JwtAuthFilter jwtAuthFilter() {
        return new JwtAuthFilter();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserAccountService();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    } 

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public DolibarrAuthenticationProvier dolibarrAuthenticationProvier(){
        return new DolibarrAuthenticationProvier();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return new ProviderManager(Arrays.asList(dolibarrAuthenticationProvier(), authenticationProvider() ));
    }


    @Bean
    @Order(1)
    SecurityFilterChain apiSecurityFilterChain(HttpSecurity http) throws Exception {
        return http
            .csrf(csrf -> csrf.disable())
            .securityMatcher("/api/**")
            .authorizeHttpRequests(auth -> {
                auth.requestMatchers(new AntPathRequestMatcher("/api/authentication/**", "POST")).permitAll();
                auth.requestMatchers(new AntPathRequestMatcher("/api/register", "POST")).permitAll();
                auth.requestMatchers(new AntPathRequestMatcher("/api/dictionnary/**", "GET")).permitAll();
                auth.requestMatchers(new AntPathRequestMatcher("/api/accountActivation/*", "PATCH")).permitAll();
                auth.anyRequest().authenticated();
            })
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .httpBasic(Customizer.withDefaults())
            .addFilterBefore(jwtAuthFilter(), UsernamePasswordAuthenticationFilter.class)
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
