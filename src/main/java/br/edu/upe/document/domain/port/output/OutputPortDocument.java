package br.edu.upe.document.domain.port.output;

import br.edu.upe.document.adapter.input.dto.DocumentRequest;

/**
 * Output Port interface responsible for the physical generation and processing of documents.
 * * <p>In the context of Hexagonal Architecture, this port acts as a contract for
 * secondary adapters (infrastructure) that handle low-level document manipulation,
 * such as loading .docx templates via Apache POI, replacing placeholders, and
 * converting the final output to PDF.</p>
 */
public interface OutputPortDocument {

    /**
     * Processes the given document request to generate a populated binary file.
     * * <p>This method performs the following operations:</p>
     * <ul>
     * <li>Identifies and loads the correct template based on the document type.</li>
     * <li>Replaces placeholders in the template with the provided field values.</li>
     * <li>Preserves formatting (styles, tables, headers) during processing.</li>
     * <li>Optionally converts the output (e.g., from DOCX to PDF) based on the adapter implementation.</li>
     * </ul>
     *
     * @param documentRequest The data transfer object (DTO) containing the document type
     * and the map of fields (key-value) to be substituted in the template.
     * @return A {@code byte[]} array containing the binary data of the generated document,
     * ready to be streamed to the client or saved.
     * @throws RuntimeException If the template is not found, or if an error occurs
     * during file manipulation or conversion (e.g., IO errors, LibreOffice failures).
     */
    byte[] processDocument(DocumentRequest documentRequest);

}