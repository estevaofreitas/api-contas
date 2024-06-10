package br.com.nextcomputing.api.contas.domain.persistence;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import br.com.nextcomputing.api.contas.domain.model.Conta;

public interface ContaPersistence {

    public Conta findById(Long id);

    public Conta create(Conta conta);

    public Conta update(Conta conta);

    public List<Conta> filter(int pageNumber, int pageSize, LocalDate dataVencimento, String descricao);

    public BigDecimal filterByPagamentos(LocalDate dataInicioPeriodo, LocalDate dataFimPeriodo);

}
