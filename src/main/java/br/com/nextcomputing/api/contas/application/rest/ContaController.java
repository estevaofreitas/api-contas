package br.com.nextcomputing.api.contas.application.rest;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvParser;

import br.com.nextcomputing.api.contas.application.rest.dto.ContaCreateRequest;
import br.com.nextcomputing.api.contas.application.rest.dto.ContaFiltroRequest;
import br.com.nextcomputing.api.contas.application.rest.dto.ContaResponse;
import br.com.nextcomputing.api.contas.application.rest.dto.ContaUpdateRequest;
import br.com.nextcomputing.api.contas.application.rest.dto.PagamentoFiltroRequest;
import br.com.nextcomputing.api.contas.application.rest.dto.PagamentoFiltroResponse;
import br.com.nextcomputing.api.contas.application.rest.exception.ContaNotFoundException;
import br.com.nextcomputing.api.contas.domain.enumeration.SituacaoConta;
import br.com.nextcomputing.api.contas.domain.model.Conta;
import br.com.nextcomputing.api.contas.domain.service.ContaService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ContaController implements ContaApi{
    
    private final ContaService service;

    private final ModelMapper mapper = new ModelMapper();
    
    @Override
    public ContaResponse findById(long id) {
        Conta conta = service.findById(id);
        if(conta == null) 
            throw new ContaNotFoundException();
        return toDto(conta);
    }

    @Override
    public ContaResponse create(ContaCreateRequest request) {
        Conta conta = mapper.map(request, Conta.class);
        conta.setSituacao(SituacaoConta.of(request.getSituacao()));
        return toDto(service.create(conta));
    }

    @Override
    public ContaResponse update(ContaUpdateRequest request) {
        Conta conta = mapper.map(request, Conta.class);
        conta.setSituacao(SituacaoConta.of(request.getSituacao()));
        return toDto(service.update(conta));
    }

    @Override
    public ContaResponse updateSituacao(long id, String situacao) {
        Conta conta = new Conta();
        conta.setId(id);
        conta.setSituacao(SituacaoConta.of(situacao));
        return toDto(service.updateSituacao(conta));
    }

    @Override
    public List<ContaResponse> filterConta(Integer pageNumber, Integer pageSize, ContaFiltroRequest request) {
        if(pageSize == null)
            pageSize = 10;
        if(pageNumber == null)
            pageNumber = 0;
                
        List<Conta> resultFilter = service.filter(pageNumber, pageSize, request.getDataVencimento(), request.getDescricao());
        
        List<ContaResponse> result = new ArrayList<>();
        for (Conta conta : resultFilter) {
            result.add(toDto(conta));
        }
        return result;
    }

    @Override
    public PagamentoFiltroResponse filterPagamento(PagamentoFiltroRequest request) {        
        BigDecimal resultado = service.filterByPagamentos(request.getDataInicioPeriodo(), request.getDataFimPeriodo());
        
        PagamentoFiltroResponse result = new PagamentoFiltroResponse();
        result.setDataInicioPeriodo(request.getDataInicioPeriodo());
        result.setDataFimPeriodo(request.getDataFimPeriodo());
        result.setValorTotal(resultado);

        return result;
    }

    @Override
    public void importFile(MultipartFile file) {
       DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-mm-dd");
       
        ObjectMapper objectMapper = new CsvMapper();
        try (MappingIterator<List<String>> it = objectMapper.readerForListOf(String.class)
        .with(CsvParser.Feature.WRAP_AS_ARRAY).readValues(file.getInputStream())) {
            while(it.hasNext()){
                List<String> values = it.next();
                String dataPagamento = values.get(0);
                String dataVencimento = values.get(1);
                String descricao = values.get(2);
                String situacao = values.get(3);
                String valor = values.get(4);

                Conta conta = new Conta();
                if(!StringUtils.isEmpty(dataPagamento))
                    conta.setDataPagamento(LocalDate.parse(dataPagamento, formatter));
                
                if(!StringUtils.isEmpty(dataVencimento))
                   conta.setDataVencimento(LocalDate.parse(dataPagamento, formatter));
                
                if(!StringUtils.isEmpty(descricao))
                    conta.setDescricao(descricao);
                
                if(!StringUtils.isEmpty(situacao))
                    conta.setSituacao(SituacaoConta.of(situacao));
                
                if(!StringUtils.isEmpty(valor))
                    conta.setValor(new BigDecimal(valor));

                service.create(conta);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private ContaResponse toDto(Conta model){
        ContaResponse result = mapper.map(model, ContaResponse.class);
        result.setSituacao(model.getSituacao().getValue());
        return result;
    }
}