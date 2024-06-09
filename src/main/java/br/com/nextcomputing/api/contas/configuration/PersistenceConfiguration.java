package br.com.nextcomputing.api.contas.configuration;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = "br.com.nextcomputing.api.contas.application.persistence.repository") 
public class PersistenceConfiguration {
    
}
