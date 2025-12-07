package br.edu.upe.document.domain.port.input;

/**
 * This interface defines the input port for document template.
 */
public interface InputPortTemplate {

    String getTemplate(String template) throws Exception;

}
