package br.com.nextcomputing.api.contas.domain.model;

import java.math.BigDecimal;
import java.util.Date;

import br.com.nextcomputing.api.contas.domain.enumeration.SituacaoConta;
import lombok.Data;

@Data
public class Conta {
    
    private Long id;
    private Date dataVencimento;
    private Date dataPagamento;
    private BigDecimal valor;
    private String descricao;
    private SituacaoConta situacao;

}
