package br.com.nextcomputing.api.contas.application.rest.dto;

import java.math.BigDecimal;
import java.util.Date;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description="Conta")
public class ContaDto {
    
    @Schema(name = "id", description = "ID da conta")
    private Long id;

    @Schema(name = "dataVencimento", description = "Data de vencimento da conta")
    private Date dataVencimento;
    
    @Schema(name = "dataPagamento", description = "Data de pagamento da conta")
    private Date dataPagamento;
    
    @Schema(name = "valor", description = "Valor da conta")
    private BigDecimal valor;
    
    @Schema(name = "descricao", description = "Descrição da conta")
    private String descricao;
    
    @Schema(name = "situacao", description = "Situacao da conta")
    private String situacao;
    
}