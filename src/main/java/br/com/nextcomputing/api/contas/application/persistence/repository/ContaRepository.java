package br.com.nextcomputing.api.contas.application.persistence.repository;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.nextcomputing.api.contas.application.persistence.entity.ContaEntity;

@Repository
public interface ContaRepository extends JpaRepository<ContaEntity, Long>{
    
    @Query(value = "SELECT SUM(c.valor) FROM contas c WHERE c.situacao = 'P' and c.data_pagamento >= :dataInicioPeriodo and c.data_pagamento <= :dataFimPeriodo", nativeQuery = true)
    public BigDecimal filterByPagamentos(LocalDate dataInicioPeriodo, LocalDate dataFimPeriodo);

}
