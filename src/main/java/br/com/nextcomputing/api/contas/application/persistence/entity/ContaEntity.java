package br.com.nextcomputing.api.contas.application.persistence.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import br.com.nextcomputing.api.contas.application.persistence.converter.SituacaoContaConverter;
import br.com.nextcomputing.api.contas.domain.enumeration.SituacaoConta;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity(name = "Conta")
@Table(name = "contas")
@Data
public class ContaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "data_vencimento", nullable = false)
    private LocalDate dataVencimento;
    
    @Column(name = "data_pagamento")
    private LocalDate dataPagamento;
    
    @Column(name = "valor", nullable = false)
    private BigDecimal valor;
    
    @Column(name = "descricao", nullable = false, length = 200)
    private String descricao;
    
    @Column(name = "situacao", nullable = false)
    @Convert(converter = SituacaoContaConverter.class)
    private SituacaoConta situacao;
    
}
