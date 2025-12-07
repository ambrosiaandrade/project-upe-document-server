package br.edu.upe.document.adapter.input.dto;

import br.edu.upe.document.domain.model.DocumentTypeEnum;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public class DocumentRequest {

    @JsonProperty("document_type")
    private DocumentTypeEnum documentType;
    private Map<String, String> fields;

    public DocumentTypeEnum getDocumentType() {
        return documentType;
    }

    public void setDocumentType(DocumentTypeEnum documentType) {
        this.documentType = documentType;
    }

    public Map<String, String> getFields() {
        return fields;
    }

    public void setFields(Map<String, String> fields) {
        this.fields = fields;
    }

}
