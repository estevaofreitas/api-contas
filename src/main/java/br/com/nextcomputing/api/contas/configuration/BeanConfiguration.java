package br.com.nextcomputing.api.contas.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.nextcomputing.api.contas.application.persistence.ContaJpaPersistence;
import br.com.nextcomputing.api.contas.application.persistence.repository.ContaRepository;
import br.com.nextcomputing.api.contas.application.rest.ContaController;
import br.com.nextcomputing.api.contas.domain.persistence.ContaPersistence;
import br.com.nextcomputing.api.contas.domain.service.ContaService;

@Configuration
public class BeanConfiguration {
    
    @Bean
    ContaJpaPersistence contaJpaPersistence(ContaRepository contaRepository){
        return new ContaJpaPersistence(contaRepository);
    }

    @Bean
    ContaService contaService(ContaPersistence contaPersistence){
        return new ContaService(contaPersistence);
    }

    @Bean
    ContaController contaController(ContaService contaService){
        return new ContaController(contaService);
    }

}
