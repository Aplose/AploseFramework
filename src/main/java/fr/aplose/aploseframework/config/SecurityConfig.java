package fr.aplose.aploseframework.config;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
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
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import fr.aplose.aploseframework.filter.JwtAuthFilter;
import fr.aplose.aploseframework.security.DolibarrAuthenticationProvier;

/**
 *
 * @author oandrade
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    
    @Autowired
    @Lazy
    UserDetailsService userDetailsService;  

    private static CorsConfigurationSource corsConfigurationSource;
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        if(corsConfigurationSource == null){
            CorsConfiguration configuration = new CorsConfiguration();
            configuration.setAllowedOrigins(Arrays.asList("*")); // À adapter selon vos besoins
            configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
            configuration.setAllowedHeaders(Arrays.asList("*"));
            configuration.setExposedHeaders(Arrays.asList("Authorization"));
            configuration.setAllowCredentials(false);            
            UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
            source.registerCorsConfiguration("/api/**", configuration);
            corsConfigurationSource = source;
        }
        return corsConfigurationSource;
    }

    @Bean
    public JwtAuthFilter jwtAuthFilter() {
        return new JwtAuthFilter();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    } 

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
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
        .cors(cors -> cors.configurationSource(corsConfigurationSource()))
        .csrf(csrf -> {
                csrf.disable();
            })
            .securityMatcher( "/api/**")
            .authorizeHttpRequests(auth -> {
                auth.requestMatchers(new AntPathRequestMatcher("/api/authentication/**", "POST")).permitAll();
                auth.requestMatchers(new AntPathRequestMatcher("/api/google-extract-claims", "POST")).permitAll();
                auth.requestMatchers(new AntPathRequestMatcher("/api/google-register", "POST")).permitAll();
                auth.requestMatchers(new AntPathRequestMatcher("/api/webhook/**", "POST")).permitAll();
                auth.requestMatchers(new AntPathRequestMatcher("/api/register", "POST")).permitAll();
                auth.requestMatchers(new AntPathRequestMatcher("/api/dictionnary/**", "GET")).permitAll();
                auth.requestMatchers(new AntPathRequestMatcher("/api/account-activation/*", "PATCH")).permitAll();
                auth.requestMatchers(new AntPathRequestMatcher("/api/ping", "GET")).permitAll();
                auth.requestMatchers(new AntPathRequestMatcher("/api/translation", "GET")).permitAll();
                auth.requestMatchers(new AntPathRequestMatcher("/api/translation/all", "GET")).permitAll();
                auth.requestMatchers(new AntPathRequestMatcher("/api/config/**", "GET")).permitAll();
                auth.requestMatchers(new AntPathRequestMatcher("/api/dolibarr/**", "GET")).permitAll();
                auth.requestMatchers(new AntPathRequestMatcher("/api/dolibarr/**", "POST")).permitAll();
                auth.requestMatchers(new AntPathRequestMatcher("/api/dolibarr/**", "PUT")).permitAll();
                auth.requestMatchers(new AntPathRequestMatcher("/api/vizulive", "POST")).permitAll();
                auth.requestMatchers(new AntPathRequestMatcher("/api/vizulive/**", "POST")).permitAll();
                auth.requestMatchers(new AntPathRequestMatcher("/api/vizulive/**", "GET")).permitAll();
                auth.requestMatchers(new AntPathRequestMatcher("/api/vizulive/**", "DELETE")).permitAll();
                auth.requestMatchers(new AntPathRequestMatcher("/api/rules/**", "POST")).permitAll();
                auth.anyRequest().authenticated();
            })
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .httpBasic(Customizer.withDefaults())
            .addFilterBefore(jwtAuthFilter(), UsernamePasswordAuthenticationFilter.class)
            .build();
    }
    @Bean
    @Order(2)
    SecurityFilterChain dbConsoleSecurityFilterChain(HttpSecurity http) throws Exception {
        return http.securityMatcher(AntPathRequestMatcher.antMatcher("/h2-console/**"))
            .authorizeHttpRequests( auth -> {
                auth.requestMatchers(AntPathRequestMatcher.antMatcher("/h2-console/**")).permitAll();
                auth.requestMatchers(AntPathRequestMatcher.antMatcher("/phpmyadmin/**")).hasRole("SUPER_ADMIN");
            })
            .csrf(csrf -> {
                csrf.ignoringRequestMatchers(AntPathRequestMatcher.antMatcher("/h2-console/**"));
                csrf.ignoringRequestMatchers(AntPathRequestMatcher.antMatcher("/phpmyadmin/**"));
                    })
            .headers(headers -> headers.frameOptions(frameOptions -> frameOptions.disable()))
            .build();
    }  

    @Bean
    @Order(3)
    SecurityFilterChain configureSecurityAF(HttpSecurity http) throws Exception {
      http.authorizeHttpRequests(request -> request 
        .requestMatchers("/login","/assets/**","/favicon.ico").permitAll()
        .requestMatchers("/logout").authenticated()
        .requestMatchers("/admin/**").hasRole("SUPER_ADMIN")
        .anyRequest().authenticated())
        .httpBasic(Customizer.withDefaults())
        .formLogin(Customizer.withDefaults());
        return http.build();
    }
}
