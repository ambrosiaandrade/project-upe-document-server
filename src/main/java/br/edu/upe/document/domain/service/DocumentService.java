package br.edu.upe.document.domain.service;

import br.edu.upe.document.adapter.input.dto.DocumentRequest;
import br.edu.upe.document.domain.constants.Fields;
import br.edu.upe.document.domain.model.DocumentTypeEnum;
import br.edu.upe.document.domain.port.input.InputPortDocument;
import br.edu.upe.document.domain.port.output.OutputPortData;
import br.edu.upe.document.domain.port.output.OutputPortDocument;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DocumentService implements InputPortDocument {

    private OutputPortDocument outputPortDocument;
    private OutputPortData outputPortData;

    public DocumentService(OutputPortDocument outputPortDocument, OutputPortData outputPortData) {
        this.outputPortDocument = outputPortDocument;
        this.outputPortData = outputPortData;
    }

    @Override
    public byte[] generate(DocumentRequest documentRequest) {

        try {
            Map<String, String> fields = uppercaseKeys(documentRequest.getFields());
            enrichWithBusinessRules(documentRequest.getDocumentType(), fields);
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

    private void enrichWithBusinessRules(DocumentTypeEnum type, Map<String, String> map) {
        // Regra de Data Corrente
        if (!map.containsKey("DDMMYYYY") || map.get("DDMMYYYY").isBlank()) {
            map.put("DDMMYYYY", "___/___/" + LocalDate.now().getYear());
        }

        // Regra de Campus Padrão (Idealmente viria do usuário logado ou config)
        map.putIfAbsent("IE_CAMPUS", "Petrolina");

        // Regra Específica: Dados do Banco para Termo de Compromisso
        if (DocumentTypeEnum.TERMO_COMPROMISSO_ESTAGIO.equals(type)) {
            // Busca dados externos via Porta (Hexagonal puro)
            Map<String, String> dbData = outputPortData.getInsurancePolicyData(map.get("IE_CAMPUS"));

            // Mescla os dados do banco no mapa atual se não existirem
            if (dbData != null) {
                dbData.forEach(map::putIfAbsent);
            }
        }
    }

    private void validateKeys(DocumentTypeEnum documentType, Map<String, String> map) {
        List<String> requiredKeys = switch (documentType) {
            case DECLARACAO_SUPERVISAO -> Fields.DECLARACAO_SUPERVISAO;
            case TERMO_COMPROMISSO_ESTAGIO -> Fields.TERMO_COMPROMISSO_ESTAGIO;
            case TERMO_ENCAMINHAMENTO_ESTAGIO -> Fields.TERMO_ENCAMINHAMENTO_ESTAGIO;
        };

        List<String> missingKeys = new ArrayList<>();

        for (String required : requiredKeys) {
            if (!map.containsKey(required)) {
                missingKeys.add(required);
            }
        }

        if (!missingKeys.isEmpty()) {
            throw new RuntimeException("[" + documentType.name() + "] " + "Missing mandatory field: " + String.join(", ", missingKeys));
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