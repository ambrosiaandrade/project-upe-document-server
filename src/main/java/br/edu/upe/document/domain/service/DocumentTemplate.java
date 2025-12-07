package br.edu.upe.document.domain.service;

import br.edu.upe.document.domain.constants.Templates;
import br.edu.upe.document.domain.port.input.InputPortTemplate;

import java.util.HashMap;
import java.util.Map;

public class DocumentTemplate implements InputPortTemplate {

    private static final Map<String, String> templates = new HashMap<>();

    public DocumentTemplate() {
        templates.put("DECLARACAO_SUPERVISAO", Templates.DECLARACAO_SUPERVISAO);;
    }

    @Override
    public String getTemplate(String template) throws Exception {
        var templateJson = templates.get(normalize(template));
        if (templateJson == null) {
            throw new Exception("Template not found");
        }
        return templateJson;
    }

    private String normalize(String template) {
        return template.toUpperCase().replace("-","_");
    }

}
