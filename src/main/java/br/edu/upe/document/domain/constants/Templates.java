package br.edu.upe.document.domain.constants;

public class Templates {

    public static final String DECLARACAO_SUPERVISAO = """
        {
          "document_type": "DECLARACAO_SUPERVISAO",
          "fields": [
            {
              "name": "es_nome",
              "type": "text",
              "label": "Nome do(a) Estudante",
              "value": "",
              "required": true,
              "editable_by": ["STUDENT"]
            },
            {
              "name": "es_disciplina",
              "type": "text",
              "label": "Disciplina do Estágio",
              "value": "",
              "required": true,
              "editable_by": ["STUDENT"]
            },
            {
              "name": "ps_nome",
              "type": "text",
              "label": "Nome do Supervisor(a)",
              "value": "",
              "required": true,
              "editable_by": ["STUDENT"]
            },
            {
              "name": "ps_formacao",
              "type": "text",
              "label": "Formação do Supervisor(a)",
              "value": "",
              "required": true,
              "editable_by": ["STUDENT"]
            },
            {
              "name": "ps_cpf",
              "type": "text",
              "label": "CPF do Supervisor(a)",
              "value": "",
              "required": true,
              "editable_by": ["STUDENT"]
            },
            {
              "name": "ps_telefone",
              "type": "text",
              "label": "Telefone do Supervisor(a)",
              "value": "",
              "required": true,
              "editable_by": ["STUDENT"]
            },
            {
              "name": "ps_email",
              "type": "text",
              "label": "E-mail do Supervisor(a)",
              "value": "",
              "required": true,
              "editable_by": ["STUDENT"]
            },
            {
              "name": "uc_nome",
              "type": "text",
              "label": "Nome da Unidade Curricular",
              "value": "",
              "required": true,
              "editable_by": ["STUDENT"]
            },
            {
              "name": "uc_cidade",
              "type": "text",
              "label": "Cidade da Unidade Curricular",
              "value": "",
              "required": true,
              "editable_by": ["STUDENT"]
            },
            {
              "name": "ddmmyyyy",
              "type": "date",
              "label": "Data da assinatura",
              "value": "",
              "required": true,
              "editable_by": ["STUDENT"]
            }
          ]
        }
        """;

}
