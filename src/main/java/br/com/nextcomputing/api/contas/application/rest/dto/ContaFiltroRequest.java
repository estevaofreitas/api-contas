package br.com.nextcomputing.api.contas.application.rest.dto;

import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description="Filtro de Contas")
public class ContaFiltroRequest {

    @Schema(name = "dataVencimento", description = "Data de vencimento da conta")
    private LocalDate dataVencimento;
    
    @Schema(name = "descricao", description = "Descrição da conta")
    private String descricao;
    
}