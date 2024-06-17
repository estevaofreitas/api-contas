package br.com.nextcomputing.api.contas.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    public static final String ADMIN = "role-api-contas-admin";
    private final SecurityJwtConverter jwtConverter;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((authz) ->
                authz
                .requestMatchers(HttpMethod.GET, "/api/**").hasRole(ADMIN)
                .requestMatchers(HttpMethod.POST, "/api/**").hasRole(ADMIN)
                .requestMatchers(HttpMethod.PATCH, "/api/**").hasRole(ADMIN)
                .requestMatchers(HttpMethod.DELETE, "/api/**").hasRole(ADMIN)
                .anyRequest().authenticated());

        http.sessionManagement(sess -> sess.sessionCreationPolicy(
                SessionCreationPolicy.STATELESS));
        http.oauth2ResourceServer(oauth2 -> oauth2.jwt(jwt -> jwt.jwtAuthenticationConverter(jwtConverter)));

        return http.build();
    }

}
