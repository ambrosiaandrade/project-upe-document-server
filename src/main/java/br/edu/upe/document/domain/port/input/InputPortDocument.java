package br.edu.upe.document.domain.port.input;

import br.edu.upe.document.adapter.input.dto.DocumentRequest;

/**
 * This interface defines the input port for document generation.
 */
public interface InputPortDocument {

    /**
     * Generates a document of a specific type with provided fields.
     * @param documentRequest The request object containing document type and fields.
     * @return A byte array representing the generated document.
     * @throws Exception If an error occurs during document generation.
     */
    byte[] generate(DocumentRequest documentRequest) throws Exception;

}
