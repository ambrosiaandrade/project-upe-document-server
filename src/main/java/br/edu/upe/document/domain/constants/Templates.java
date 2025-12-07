package br.edu.upe.document.domain.constants;

public class Templates {

    private static final String ES_DISCIPLINA = """
        {
          "name": "es_disciplina",
          "type": "select",
          "label": "Disciplina do Estágio",
          "options": [
            "Estágio Supervisionado I",
            "Estágio Supervisionado II",
            "Estágio Supervisionado III",
            "Estágio Supervisionado IV"
          ],
          "value": "",
          "required": true,
          "editable_by": ["STUDENT"]
        }
        """;

    private static final String ES_CURSO = """
        {
          "name": "es_curso",
          "type": "select",
          "label": "Curso do Estudante",
          "options": [
            "Ciências Biológicas",
            "Geografia",
            "História",
            "Matemática",
            "Pedagogia",
            "Português e Espanhol",
            "Português e Inglês",
            "Educação do Campo"
          ],
          "value": "",
          "required": true,
          "editable_by": ["STUDENT"]
         }
        """;

    private static final String DDMMYYYY =
        """
        {
              "name": "ddmmyyyy",
              "type": "date",
              "label": "Data do Documento",
              "placeholder": "15/03/2026",
              "value": "",
              "required": true,
              "editable_by": ["STUDENT"]
        }
        """;

    public static final String DECLARACAO_SUPERVISAO = String.format("""
        {
          "document_type": "DECLARACAO_SUPERVISAO",
          "fields": [
            %s,
            %s,
            {
              "name": "es_nome",
              "type": "text",
              "label": "Nome do(a) Estudante",
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
              "type": "tel",
              "label": "Telefone do Supervisor(a)",
              "pattern": "$\\d{2}$ \\d{5}-\\d{4}"
              "value": "",
              "required": true,
              "editable_by": ["STUDENT"]
            },
            {
              "name": "ps_email",
              "type": "email",
              "label": "E-mail do Supervisor(a)",
              "value": "",
              "required": true,
              "editable_by": ["STUDENT"]
            },
            {
              "name": "uc_nome",
              "type": "text",
              "label": "Nome da Unidade Concedente",
              "value": "",
              "required": true,
              "editable_by": ["STUDENT"]
            },
            {
              "name": "uc_cidade",
              "type": "text",
              "label": "Cidade da Unidade Concedente",
              "value": "",
              "required": true,
              "editable_by": ["STUDENT"]
            }
          ]
        }
        """, ES_DISCIPLINA, DDMMYYYY);

    public static final String TERMO_COMPROMISSO_ESTAGIO = String.format("""
        {
          "document_type": "TERMO_COMPROMISSO_ESTAGIO",
          "fields": [
            %s,
            %s,
            %s,
            {
              "name": "es_bairro",
              "type": "text",
              "label": "Bairro do Estudante",
              "value": "",
              "required": true,
              "editable_by": ["STUDENT"]
            },
            {
              "name": "es_cep",
              "type": "text",
              "label": "CEP do Estudante",
              "value": "",
              "required": true,
              "editable_by": ["STUDENT"]
            },
            {
              "name": "es_complemento",
              "type": "text",
              "label": "Complemento do Endereço do Estudante",
              "value": "",
              "required": false,
              "editable_by": ["STUDENT"]
            },
            {
              "name": "es_cpf",
              "type": "text",
              "label": "CPF do Estudante",
              "value": "",
              "required": true,
              "editable_by": ["STUDENT"]
            },
            {
              "name": "es_data_final",
              "type": "date",
              "label": "Data Final do Estágio",
              "value": "",
              "required": true,
              "editable_by": ["STUDENT"]
            },
            {
              "name": "es_data_inicial",
              "type": "date",
              "label": "Data Inicial do Estágio",
              "value": "",
              "required": true,
              "editable_by": ["STUDENT"]
            },
            {
              "name": "es_horario_estagio",
              "type": "text",
              "label": "Horário e dias do estágio",
              "placeholder": "Segunda-feira: 10h às 12h, Quarta-feira: 15:30 às 16:40"
              "value": "",
              "required": true,
              "editable_by": ["STUDENT"]
            },
            {
              "name": "es_total_hora_semana",
              "type": "text",
              "label": "A soma dos horários de cada dia do estágio",
              "value": "",
              "required": true,
              "editable_by": ["STUDENT"]
            },
            {
              "name": "es_email",
              "type": "email",
              "label": "E-mail do Estudante",
              "value": "",
              "required": true,
              "editable_by": ["STUDENT"]
            },
            {
              "name": "es_endereco",
              "type": "text",
              "label": "Endereço do Estudante",
              "value": "",
              "required": true,
              "editable_by": ["STUDENT"]
            },
            {
              "name": "es_nome",
              "type": "text",
              "label": "Nome do Estudante",
              "value": "",
              "required": true,
              "editable_by": ["STUDENT"]
            },
            {
              "name": "es_numero",
              "type": "text",
              "label": "Número do Endereço do Estudante",
              "value": "",
              "required": true,
              "editable_by": ["STUDENT"]
            },
            {
              "name": "es_periodo",
              "type": "text",
              "label": "Período do Estágio",
              "value": "",
              "required": true,
              "editable_by": ["STUDENT"]
            },
            {
              "name": "es_telefone",
              "type": "tel",
              "label": "Telefone do Estudante",
              "pattern": "$\\d{2}$ \\d{5}-\\d{4}"
              "value": "",
              "required": true,
              "editable_by": ["STUDENT"]
            },
            {
              "name": "ps_cargo",
              "type": "text",
              "label": "Cargo do Supervisor(a)",
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
              "name": "ps_email",
              "type": "email",
              "label": "E-mail do Supervisor(a)",
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
              "name": "ps_nome",
              "type": "text",
              "label": "Nome do Supervisor(a)",
              "value": "",
              "required": true,
              "editable_by": ["STUDENT"]
            },
            {
              "name": "ps_telefone",
              "type": "tel",
              "label": "Telefone do Supervisor(a)",
              "pattern": "$\\d{2}$ \\d{5}-\\d{4}"
              "value": "",
              "required": true,
              "editable_by": ["STUDENT"]
            },
            {
              "name": "pu_comp_curric_email",
              "type": "email",
              "label": "E-mail do Coordenador Curricular",
              "pattern": "\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}"
              "value": "",
              "required": true,
              "editable_by": ["STUDENT"]
            },
            {
              "name": "pu_orientador_email",
              "type": "email",
              "label": "E-mail do Orientador",
              "value": "",
              "required": true,
              "editable_by": ["STUDENT"]
            },
            {
              "name": "pu_comp_curric",
              "type": "text",
              "label": "Nome do Coordenador Curricular",
              "value": "",
              "required": true,
              "editable_by": ["STUDENT"]
            },
            {
              "name": "pu_orientador",
              "type": "text",
              "label": "Nome do Orientador",
              "value": "",
              "required": true,
              "editable_by": ["STUDENT"]
            },
            {
              "name": "uc_bairro",
              "type": "text",
              "label": "Bairro da Unidade Concedente",
              "value": "",
              "required": true,
              "editable_by": ["STUDENT"]
            },
            {
              "name": "uc_cargo_representante",
              "type": "text",
              "label": "Cargo do Representante Legal",
              "value": "",
              "required": true,
              "editable_by": ["STUDENT"]
            },
            {
              "name": "uc_cep",
              "type": "text",
              "label": "CEP da Unidade Concedente",
              "value": "",
              "required": true,
              "editable_by": ["STUDENT"]
            },
            {
              "name": "uc_cidade",
              "type": "text",
              "label": "Cidade da Unidade Concedente",
              "value": "",
              "required": true,
              "editable_by": ["STUDENT"]
            },
            {
              "name": "uc_cnpj",
              "type": "text",
              "label": "CNPJ da Unidade Concedente",
              "value": "",
              "required": true,
              "editable_by": ["STUDENT"]
            },
            {
              "name": "uc_complemento",
              "type": "text",
              "label": "Complemento da Unidade Concedente",
              "value": "",
              "required": false,
              "editable_by": ["STUDENT"]
            },
            {
              "name": "uc_endereco",
              "type": "text",
              "label": "Endereço da Unidade Concedente",
              "value": "",
              "required": true,
              "editable_by": ["STUDENT"]
            },
            {
              "name": "uc_estado",
              "type": "text",
              "label": "Estado da Unidade Concedente",
              "value": "",
              "required": true,
              "editable_by": ["STUDENT"]
            },
            {
              "name": "uc_local_estagio",
              "type": "text",
              "label": "Local do Estágio",
              "value": "",
              "required": true,
              "editable_by": ["STUDENT"]
            },
            {
              "name": "uc_nome",
              "type": "text",
              "label": "Nome da Unidade Concedente",
              "value": "",
              "required": true,
              "editable_by": ["STUDENT"]
            },
            {
              "name": "uc_numero",
              "type": "text",
              "label": "Número da Unidade Concedente",
              "value": "",
              "required": true,
              "editable_by": ["STUDENT"]
            },
            {
              "name": "uc_ponto_referencia",
              "type": "text",
              "label": "Ponto de Referência da Unidade Concedente",
              "value": "",
              "required": false,
              "editable_by": ["STUDENT"]
            },
            {
              "name": "uc_representante_legal",
              "type": "text",
              "label": "Nome do Representante Legal",
              "value": "",
              "required": true,
              "editable_by": ["STUDENT"]
            },
            {
              "name": "uc_telefone",
              "type": "tel",
              "label": "Telefone da Unidade Concedente",
              "pattern": "$\\d{2}$ \\d{5}-\\d{4}"
              "value": "",
              "required": true,
              "editable_by": ["STUDENT"]
            },
            {
              "name": "as_numero",
              "type": "text",
              "label": "Número da Apólice de Seguros",
              "value": "",
              "required": false,
              "editable_by": ["ADMINISTRATOR"]
            },
            {
              "name": "as_nome",
              "type": "text",
              "label": "Nome do Administrador do Seguro",
              "value": "",
              "required": false,
              "editable_by": ["ADMINISTRATOR"]
            }
          ]
        }
    """, ES_DISCIPLINA, ES_CURSO, DDMMYYYY);

    public static final String TERMO_ENCAMINHAMENTO_ESTAGIO = String.format("""
        {
          "document_type": "TERMO_ENCAMINHAMENTO_ESTAGIO",
          "fields": [
            %s,
            %s,
            {
              "name": "uc_nome",
              "type": "text",
              "label": "Nome da Unidade Concedente",
              "value": "",
              "required": true,
              "editable_by": ["STUDENT"]
            },
            {
              "name": "es_nome",
              "type": "text",
              "label": "Nome do Estudante",
              "value": "",
              "required": true,
              "editable_by": ["STUDENT"]
            },
            {
              "name": "es_periodo",
              "type": "text",
              "label": "Período do Estudante",
              "value": "",
              "required": true,
              "editable_by": ["STUDENT"]
            },
            {
              "name": "ce_ano_etapa",
              "type": "select",
              "label": "Ano e Etapa Escolar",
              "options": [
                "6º ano / Ensino Fundamental II",
                "7º ano / Ensino Fundamental II",
                "8º ano / Ensino Fundamental II",
                "1º ano / Ensino Médio",
                "2º ano / Ensino Médio",
                "3º ano / Ensino Médio"
              ],
              "value": "",
              "required": true,
              "editable_by": ["STUDENT"]
            },
            {
              "name": "ce_modalidade",
              "type": "select",
              "label": "Modalidade",
              "options": [
                "Integral",
                "Semi-Integral",
                "Regular",
                "Educação de Jovens e Adultos (EJA)"
              ],
              "value": "",
              "required": true,
              "editable_by": ["STUDENT"]
            },
            {
              "name": "es_data_inicial",
              "type": "date",
              "label": "Data Inicial do Estágio",
              "value": "",
              "required": true,
              "editable_by": ["STUDENT"]
            },
            {
              "name": "es_data_final",
              "type": "date",
              "label": "Data Final do Estágio",
              "value": "",
              "required": true,
              "editable_by": ["STUDENT"]
            },
            {
              "name": "yyyy",
              "type": "text",
              "label": "Ano",
              "value": "",
              "required": true,
              "editable_by": ["STUDENT"]
            },
            {
              "name": "ie_campus",
              "type": "text",
              "label": "Campus",
              "value": "Petrolina",
              "required": true,
              "editable_by": ["STUDENT"]
            }
          ]
        }
    """, ES_DISCIPLINA, ES_CURSO);

}
