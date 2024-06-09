package br.com.nextcomputing.api.contas.domain.service;

import java.util.Optional;

import br.com.nextcomputing.api.contas.domain.model.Conta;
import br.com.nextcomputing.api.contas.domain.persistence.ContaPersistence;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ContaService {

    private final ContaPersistence persistence;    
    
    public Conta findById(long id) {
        return persistence.findById(id);
    }
    
}
