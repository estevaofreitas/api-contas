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

    public static final String ROLE_CONTAS = "role-api-contas";
    public static final String PATH_CONTAS_API = "/api/contas/**";
    private final SecurityJwtConverter jwtConverter;
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests( authz ->
                authz
                .requestMatchers(HttpMethod.GET, PATH_CONTAS_API).hasRole(ROLE_CONTAS)
                .requestMatchers(HttpMethod.POST, PATH_CONTAS_API).hasRole(ROLE_CONTAS)
                .requestMatchers(HttpMethod.PATCH, PATH_CONTAS_API).hasRole(ROLE_CONTAS)
                .requestMatchers(HttpMethod.DELETE, PATH_CONTAS_API).hasRole(ROLE_CONTAS)
                .anyRequest().authenticated());

        http.sessionManagement(sess -> sess.sessionCreationPolicy(
                SessionCreationPolicy.STATELESS));
        http.oauth2ResourceServer(oauth2 -> oauth2.jwt(jwt -> jwt.jwtAuthenticationConverter(jwtConverter)));

        return http.build();
    }

}
