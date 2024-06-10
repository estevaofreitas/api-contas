package br.com.nextcomputing.api.contas.domain.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.nextcomputing.api.contas.domain.enumeration.SituacaoConta;
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

    @Test
    void givenValidConta_whenUpdateSituacaoConta_thenSucceed(){
        Conta conta = new Conta();
        conta.setId(1L);
        conta.setSituacao(SituacaoConta.PAGA);

        Conta retorno1 = new Conta();
        retorno1.setId(1L);
        retorno1.setSituacao(SituacaoConta.NAO_PAGA);

        Conta retorno2 = new Conta();
        retorno2.setId(1L);
        retorno2.setSituacao(SituacaoConta.PAGA);

        when(persistence.findById(anyLong())).thenReturn(retorno1);
        when(persistence.update(conta)).thenReturn(retorno2);

        service.updateSituacao(conta);

        verify(persistence).update(any(Conta.class));
        assertEquals(1L, retorno2.getId());
        assertEquals(SituacaoConta.PAGA, retorno2.getSituacao());        
    }

    @Test
    void givenValidConta_whenUpdateSituacaoConta_thenFail(){
        Conta conta = new Conta();
        conta.setId(1L);
        conta.setSituacao(SituacaoConta.PAGA);

        when(persistence.findById(anyLong())).thenReturn(null);
        
        assertThrows(NullPointerException.class, ()-> service.updateSituacao(conta));
        verify(persistence, never()).update(any(Conta.class));        
    }
}
