package br.com.nextcomputing.api.contas.application.rest.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(description="Requisição de criação de conta")
public class ContaCreateRequest {

    @Schema(name = "dataVencimento", description = "Data de vencimento da conta")
    @NotNull
    private LocalDate dataVencimento;
    
    @Schema(name = "dataPagamento", description = "Data de pagamento da conta")
    private LocalDate dataPagamento;
    
    @Schema(name = "valor", description = "Valor da conta")
    @NotNull
    private BigDecimal valor;
    
    @Schema(name = "descricao", description = "Descrição da conta")
    @NotBlank(message = "Descrição da conta é obrigatório.")
    @Size(min = 3, max = 200, message = "Descrição da conta deve ter de 3 a 200 caracteres.")
    private String descricao;
    
    @Schema(name = "situacao", description = "Situacao da conta")
    @NotNull
    private String situacao;
    
}