package br.com.nextcomputing.api.contas.domain.enumeration;

public enum SituacaoConta {
    NAO_PAGA ("NP"),
    PAGA("P");
    
    private String value;

    private SituacaoConta(String value){
        this.value = value;
    } 
    
    public String getValue() {
        return value;
    }

    public static SituacaoConta of(String source){
        
        for (SituacaoConta situacaoConta : SituacaoConta.values()) {
            if(situacaoConta.getValue().equalsIgnoreCase(source.toUpperCase()))
                return situacaoConta;
        }
        throw new IllegalArgumentException("Unknown SituacaoConta value: " + source.toUpperCase());
    }
}
