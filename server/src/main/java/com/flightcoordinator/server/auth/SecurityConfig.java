package com.flightcoordinator.server.auth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
    return httpSecurity
        .csrf(customizer -> customizer.disable())
        .headers(headers -> headers.contentTypeOptions(contentTypeOptions -> contentTypeOptions.disable()))
        .authorizeHttpRequests(request -> request.anyRequest().permitAll())
        .build();
  }
}
