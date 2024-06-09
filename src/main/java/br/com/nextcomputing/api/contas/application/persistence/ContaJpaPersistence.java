package br.com.nextcomputing.api.contas.application.persistence;

import org.modelmapper.ModelMapper;

import br.com.nextcomputing.api.contas.application.persistence.repository.ContaRepository;
import br.com.nextcomputing.api.contas.domain.model.Conta;
import br.com.nextcomputing.api.contas.domain.persistence.ContaPersistence;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ContaJpaPersistence implements ContaPersistence {

    private final ModelMapper mapper = new ModelMapper();
    
    private final ContaRepository repository;

    @Override
    public Conta findById(Long id) {
        return mapper.map(repository.findById(id),Conta.class);
    }
    
}