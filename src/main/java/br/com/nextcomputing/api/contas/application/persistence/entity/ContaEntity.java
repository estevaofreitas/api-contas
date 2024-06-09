package br.com.nextcomputing.api.contas.application.persistence.entity;

import java.math.BigDecimal;
import java.util.Date;

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

    @Column(name = "data_vencimento")
    private Date dataVencimento;
    
    @Column(name = "data_pagamento")
    private Date dataPagamento;
    
    @Column(name = "valor")
    private BigDecimal valor;
    
    @Column(name = "descricao")
    private String descricao;
    
    @Column(name = "situacao")
    @Convert(converter = SituacaoContaConverter.class)
    private SituacaoConta situacao;
    
}
