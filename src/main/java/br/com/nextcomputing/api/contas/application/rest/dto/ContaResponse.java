package br.com.nextcomputing.api.contas.application.rest.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description="Conta")
public class ContaResponse {
    
    @Schema(name = "id", description = "ID da conta")
    private Long id;

    @Schema(name = "dataVencimento", description = "Data de vencimento da conta")
    private LocalDate dataVencimento;
    
    @Schema(name = "dataPagamento", description = "Data de pagamento da conta")
    private LocalDate dataPagamento;
    
    @Schema(name = "valor", description = "Valor da conta")
    private BigDecimal valor;
    
    @Schema(name = "descricao", description = "Descrição da conta")
    private String descricao;
    
    @Schema(name = "situacao", description = "Situacao da conta")
    private String situacao;
    
}