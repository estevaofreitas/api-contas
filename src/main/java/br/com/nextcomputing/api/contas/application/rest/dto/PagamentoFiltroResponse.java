package br.com.nextcomputing.api.contas.application.rest.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description="Resposta de pagamentos por período")
public class PagamentoFiltroResponse {
    @Schema(name = "dataInicioPeriodo", description = "Data de início do período de pagamento")
    private LocalDate dataInicioPeriodo;

    @Schema(name = "dataFimPeriodo", description = "Data de fim do período de pagamento")
    private LocalDate dataFimPeriodo;

    @Schema(name = "valorTotal", description = "Data de fim do período de pagamento")
    private BigDecimal valorTotal;
}
