package br.com.nextcomputing.api.contas.domain.persistence;

import br.com.nextcomputing.api.contas.domain.model.Conta;

public interface ContaPersistence {

    public Conta findById(Long id);
    

}
