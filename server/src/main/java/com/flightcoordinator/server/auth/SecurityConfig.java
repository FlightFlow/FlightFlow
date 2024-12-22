package com.flightcoordinator.server.auth;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
// @EnableWebSecurity
// @EnableMethodSecurity
public class SecurityConfig {
  // @Autowired
  // private UserDetailsService userDetailsService;

  // @Autowired
  // private CustomLogoutHandler customLogoutHandler;

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
    return httpSecurity
        .csrf(customizer -> customizer.disable())
        .headers(headers -> headers.contentTypeOptions(contentTypeOptions -> contentTypeOptions.disable()))
        .authorizeHttpRequests(request -> request
            .requestMatchers(
                "/api/*/user/auth/register",
                "/api/*/user/auth/login",
                "/api/*/user/auth/newRefreshToken",
                "/api/*/user/auth/validate")
            .permitAll()
            // .requestMatchers("/swagger-ui/**",
            // "/v3/api-docs/**").hasAuthority("API_DOCS_READ")
            .anyRequest().permitAll())
        // .logout(logout -> logout
        // .logoutUrl("/api/*/user/auth/logout")
        // .addLogoutHandler(customLogoutHandler)
        // .logoutSuccessHandler((request, response, auth) ->
        // SecurityContextHolder.clearContext())
        // .logoutSuccessUrl("/api/*/user/auth/login"))
        .build();
  }

  // @Bean
  // public AuthenticationProvider authenticationProvider() {
  // DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
  // provider.setPasswordEncoder(passwordEncoder());
  // provider.setUserDetailsService(userDetailsService);
  // return provider;
  // }

  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
    return configuration.getAuthenticationManager();
  }

  @Bean
  public BCryptPasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder(12);
  }

  @Bean
  public UrlBasedCorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration configuration = new CorsConfiguration();
    configuration.setAllowedOrigins(Arrays.asList("http://localhost:5173"));
    configuration.setAllowedMethods(Arrays.asList("POST", "PATCH", "DELETE"));

    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", configuration);

    return source;
  }
}
