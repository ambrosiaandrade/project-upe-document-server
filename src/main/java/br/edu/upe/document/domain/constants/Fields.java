package br.edu.upe.document.domain.constants;

import java.util.Arrays;
import java.util.List;

public class Fields {

    // Filled by student
    public static final List<String> DECLARACAO_SUPERVISAO = Arrays.asList(
            "ES_NOME",
            "ES_DISCIPLINA",
            "PS_NOME",
            "PS_FORMACAO",
            "PS_CPF",
            "PS_TELEFONE",
            "PS_EMAIL",
            "UC_NOME",
            "UC_CIDADE",
            "DDMMYYYY");

    // Filled by student, some fields 'AS_' by administrator only
    public static final List<String> TERMO_COMPROMISSO_ESTAGIO = Arrays.asList(
            "ES_DISCIPLINA",
            "ES_BAIRRO",
            "ES_CEP",
            "ES_COMPLEMENTO",
            "ES_CPF",
            "ES_CURSO",
            "ES_DATA_FINAL",
            "ES_DATA_INICIAL",
            "ES_HORARIO_ESTAGIO",
            "ES_TOTAL_HORA_SEMANA",
            "ES_EMAIL",
            "ES_ENDERECO",
            "ES_NOME",
            "ES_NUMERO",
            "ES_PERIODO",
            "ES_TELEFONE",
            "PS_CARGO",
            "PS_CPF",
            "PS_EMAIL",
            "PS_FORMACAO",
            "PS_NOME",
            "PS_TELEFONE",
            "PU_COMP_CURRIC_EMAIL",
            "PU_ORIENTADOR_EMAIL",
            "PU_COMP_CURRIC",
            "PU_ORIENTADOR",
            "UC_BAIRRO",
            "UC_CARGO_REPRESENTANTE",
            "UC_CEP",
            "UC_CIDADE",
            "UC_CNPJ",
            "UC_COMPLEMENTO",
            "UC_ENDERECO",
            "UC_ESTADO",
            "UC_LOCAL_ESTAGIO",
            "UC_NOME",
            "UC_NUMERO",
            "UC_PONTO_REFERENCIA",
            "UC_REPRESENTANTE_LEGAL",
            "UC_TELEFONE",
            "AS_NUMERO",
            "AS_NOME",
            "IE_CAMPUS",
            "DDMMYYYY"
    );

    // Filled by student
    public static final List<String> TERMO_ENCAMINHAMENTO_ESTAGIO = Arrays.asList(
            "UC_NOME",
            "ES_NOME",
            "ES_PERIODO",
            "ES_CURSO",
            "CE_ANO_ETAPA",
            "CE_MODALIDADE",
            "ES_DISCIPLINA",
            "ES_DATA_INICIAL",
            "ES_DATA_FINAL",
            "YYYY",
            "IE_CAMPUS",
            "DDMMYYYY"
            );

}
