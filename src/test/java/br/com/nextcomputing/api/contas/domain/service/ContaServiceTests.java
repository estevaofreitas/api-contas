package br.com.nextcomputing.api.contas.domain.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.nextcomputing.api.contas.domain.model.Conta;
import br.com.nextcomputing.api.contas.domain.persistence.ContaPersistence;

@ExtendWith(MockitoExtension.class)
class ContaServiceTests {

    @Mock
    ContaPersistence persistence;

    @InjectMocks
    ContaService service;
    
    @Test
    void givenValidConta_whenCreateConta_thenSucceed(){
        Conta conta = new Conta();
        conta.setValor(BigDecimal.ONE);

        Conta retorno = new Conta();
        retorno.setId(1L);
        retorno.setValor(BigDecimal.ONE);

        when(persistence.create(conta)).thenReturn(retorno);

        service.create(conta);

        verify(persistence).create(conta);
        assertEquals(1L, retorno.getId());
    }
}
