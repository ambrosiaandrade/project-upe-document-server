package br.edu.upe.document.domain.model;

public enum DocumentTypeEnum {

    DECLARACAO_SUPERVISAO("Declaração de Supervisão"),
    TERMO_COMPROMISSO_ESTAGIO("Termo de Compromisso de Estágio"),
    TERMO_ENCAMINHAMENTO_ESTAGIO("Termo de Encaminhamento de Estágio");

    private final String description;

    DocumentTypeEnum(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

}
