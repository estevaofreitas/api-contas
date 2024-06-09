package br.com.nextcomputing.api.contas.application.persistence.converter;

import br.com.nextcomputing.api.contas.domain.enumeration.SituacaoConta;
import jakarta.persistence.AttributeConverter;

public class SituacaoContaConverter implements AttributeConverter<SituacaoConta, String> {

    @Override
    public String convertToDatabaseColumn(SituacaoConta situacaoConta) {
        return situacaoConta.getValue();
    }

    @Override
    public SituacaoConta convertToEntityAttribute(String data) {
        return SituacaoConta.of(data);
    }

}
