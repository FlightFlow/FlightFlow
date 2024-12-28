package com.flightcoordinator.server.config;


/*import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfiguration {

    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers("/api/public/**").permitAll()  // Public endpoint'ler
                .anyRequest().authenticated()  // Diğer tüm endpoint'ler kimlik doğrulaması gerektirir
            .and()
            .oauth2ResourceServer(oauth2 -> oauth2.jwt());  // JWT doğrulaması
    }

    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter() {
        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(new JwtRoleConverter());
        return jwtAuthenticationConverter;
    }
}
*/
