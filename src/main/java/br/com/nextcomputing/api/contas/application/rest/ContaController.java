package br.com.nextcomputing.api.contas.application.rest;

import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.PathVariable;

import br.com.nextcomputing.api.contas.application.rest.dto.ContaDto;
import br.com.nextcomputing.api.contas.application.rest.exception.ContaNotFoundException;
import br.com.nextcomputing.api.contas.domain.enumeration.SituacaoConta;
import br.com.nextcomputing.api.contas.domain.model.Conta;
import br.com.nextcomputing.api.contas.domain.service.ContaService;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class ContaController implements ContaApi{
    
    private final ContaService service;

    private final ModelMapper mapper = new ModelMapper();
    
    public ContaDto findById(@PathVariable long id) {
        Conta conta = service.findById(id).orElseThrow(ContaNotFoundException::new);
        return toDto(conta);
    }

    private Conta toModel(ContaDto dto){
        Conta result = mapper.map(dto, Conta.class);
        result.setSituacao(SituacaoConta.of(dto.getSituacao()));
        return result;
    }

    private ContaDto toDto(Conta model){
        ContaDto result = mapper.map(model, ContaDto.class);
        result.setSituacao(model.getSituacao().getValue());
        return result;
    }
}