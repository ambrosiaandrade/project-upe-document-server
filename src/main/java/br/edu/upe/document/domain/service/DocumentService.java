package br.edu.upe.document.domain.service;

import br.edu.upe.document.adapter.input.dto.DocumentRequest;
import br.edu.upe.document.domain.constants.Fields;
import br.edu.upe.document.domain.model.DocumentTypeEnum;
import br.edu.upe.document.domain.port.input.InputPortDocument;
import br.edu.upe.document.domain.port.output.OutputPortDocument;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DocumentService implements InputPortDocument {

    private OutputPortDocument outputPortDocument;

    public DocumentService(OutputPortDocument outputPortDocument) {
        this.outputPortDocument = outputPortDocument;
    }

    @Override
    public byte[] generate(DocumentRequest documentRequest) {

        try {
            Map<String, String> fields = uppercaseKeys(documentRequest.getFields());
            validateKeys(documentRequest.getDocumentType(), fields);
            validateFields(fields);
            documentRequest.setFields(fields);
            return outputPortDocument.processDocument(documentRequest);
        } catch (Exception e) {
            throw e;
        }

    }

    private Map<String, String> uppercaseKeys(Map<String, String> map) {
        return map.entrySet().stream()
                .collect(Collectors.toMap(
                        e -> e.getKey().toUpperCase(),
                        Map.Entry::getValue
                ));
    }

    private void validateKeys(DocumentTypeEnum documentType, Map<String, String> map) {
        List<String> requiredKeys = switch (documentType) {
            case DECLARACAO_SUPERVISAO -> Fields.DECLARACAO_SUPERVISAO;
            case TERMO_COMPROMISSO_ESTAGIO -> null;
        };

        List<String> missingKeys = new ArrayList<>();

        assert requiredKeys != null;

        for (String required : requiredKeys) {
            if (!map.containsKey(required)) {
                missingKeys.add(required);
            }
        }

        if (!missingKeys.isEmpty()) {
            throw new RuntimeException("Missing mandatory field: " + String.join(", ", missingKeys));
        }
    }

    private void validateFields(Map<String, String> map) {
        List<String> listError = new LinkedList<>();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            if (entry.getValue() == null || entry.getValue().isBlank()) {
                listError.add(entry.getKey());
            }
        }
        if (!listError.isEmpty()) {
            throw new RuntimeException("Fields: " + String.join(", ", listError));
        }
    }

}