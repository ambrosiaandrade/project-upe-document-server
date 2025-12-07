package br.edu.upe.document.domain.port.output;

import br.edu.upe.document.adapter.input.dto.DocumentRequest;

public interface OutputPortDocument {

    byte[] processDocument(DocumentRequest documentId);

}
