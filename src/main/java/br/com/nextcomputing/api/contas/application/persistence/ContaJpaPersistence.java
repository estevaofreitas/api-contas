package br.com.nextcomputing.api.contas.application.persistence;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.PageRequest;

import br.com.nextcomputing.api.contas.application.persistence.entity.ContaEntity;
import br.com.nextcomputing.api.contas.application.persistence.repository.ContaRepository;
import br.com.nextcomputing.api.contas.domain.model.Conta;
import br.com.nextcomputing.api.contas.domain.persistence.ContaPersistence;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ContaJpaPersistence implements ContaPersistence {

    private final ModelMapper mapper = new ModelMapper();

    private final ContaRepository repository;

    @Override
    public Conta findById(Long id) {
        Optional<ContaEntity> optional = repository.findById(id);
        if (!optional.isPresent())
            return null;
        return mapper.map(optional.get(), Conta.class);
    }

    @Override
    public Conta create(Conta conta) {
        ContaEntity entity = mapper.map(conta, ContaEntity.class);
        ContaEntity result = repository.save(entity);
        return mapper.map(result, Conta.class);
    }

    @Override
    public Conta update(Conta conta) {
        ContaEntity entity = mapper.map(conta, ContaEntity.class);
        ContaEntity result = repository.saveAndFlush(entity);
        return mapper.map(result, Conta.class);
    }

    @Override
    public List<Conta> filter(int pageNumber, int pageSize, LocalDate dataVencimento, String descricao) {
        ContaEntity entity = new ContaEntity();
        entity.setDataVencimento(dataVencimento);
        entity.setDescricao(descricao);

        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("descricao", match -> match.endsWith())
                .withMatcher("descricao", match -> match.startsWith());

        List<ContaEntity> entities = repository
                .findAll(Example.of(entity, matcher), PageRequest.of(pageNumber, pageSize)).getContent();
        List<Conta> result = new ArrayList<>();
        for (ContaEntity item : entities) {
            result.add(mapper.map(item, Conta.class));
        }
        return result;
    }

    @Override
    public BigDecimal filterByPagamentos(LocalDate dataInicioPeriodo, LocalDate dataFimPeriodo) {
        return repository.filterByPagamentos(dataInicioPeriodo, dataFimPeriodo);
    }

}
