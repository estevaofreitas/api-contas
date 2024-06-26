package br.com.nextcomputing.api.contas.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

import lombok.Data;

@Data
@Validated
@Configuration
@ConfigurationProperties(prefix = "security.jwt.converter")
@ConfigurationPropertiesScan
public class SecurityJwtConverterProperties {
    
    private String resourceId;
    private String principalAttribute;

}