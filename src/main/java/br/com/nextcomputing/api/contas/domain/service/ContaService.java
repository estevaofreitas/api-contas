package br.com.nextcomputing.api.contas.domain.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import br.com.nextcomputing.api.contas.domain.model.Conta;
import br.com.nextcomputing.api.contas.domain.persistence.ContaPersistence;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ContaService {

    private final ContaPersistence persistence;    
    
    public Conta findById(long id) {
        return persistence.findById(id);
    }

    public Conta create(Conta conta) {
        return persistence.create(conta);
    }

    public Conta update(Conta conta) {
        return persistence.update(conta);
    }

    public Conta updateSituacao(Conta conta) {
        Conta fromDb = findById(conta.getId());
        fromDb.setSituacao(conta.getSituacao());

        return persistence.update(fromDb);
    }

    public List<Conta> filter(int pageNumber, int pageSize, LocalDate dataVencimento, String descricao) {
        
        return persistence.filter(pageNumber, pageSize, dataVencimento, descricao);
    }

    public BigDecimal filterByPagamentos(LocalDate dataInicioPeriodo, LocalDate dataFimPeriodo) {
        
        return persistence.filterByPagamentos(dataInicioPeriodo, dataFimPeriodo);
    }
    
}
