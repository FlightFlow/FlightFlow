package com.flightcoordinator.server.auth;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true) // Updated annotation

public class SecurityConfig {

    @SuppressWarnings({ "removal", "deprecation" })
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())  
                .authorizeRequests(authorizeRequests ->
                        authorizeRequests
                        .requestMatchers("/api/v1/route/**").hasAnyAuthority("ADMIN", "OPR_DATA_MANAGER")
                        .requestMatchers("/api/v1/plane/**").hasAnyAuthority("ADMIN", "OPR_DATA_MANAGER")
                        .requestMatchers("/api/v1/vehicle/**").hasAnyAuthority("ADMIN", "OPR_DATA_MANAGER", "INFRA_MANAGER")
                        .requestMatchers("/api/v1/crew/**").hasAnyAuthority("ADMIN", "CREW_MANAGER")
                        .requestMatchers("/api/v1/certification/**").hasAnyAuthority("ADMIN", "CREW_MANAGER")
                        .requestMatchers("/api/v1/airport/**").hasAnyAuthority("ADMIN", "INFRA_MANAGER")
                        .requestMatchers("/api/v1/runway/**").hasAnyAuthority("ADMIN", "INFRA_MANAGER")
                        .anyRequest().hasAnyAuthority("ADMIN")
                )
                .oauth2ResourceServer(server -> server.jwt()); 

        return http.build();
    }
}
