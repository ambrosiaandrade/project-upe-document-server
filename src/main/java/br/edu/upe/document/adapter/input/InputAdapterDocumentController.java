package br.edu.upe.document.adapter.input;

import br.edu.upe.document.adapter.input.dto.DocumentRequest;
import br.edu.upe.document.domain.port.input.InputPortDocument;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/document")
public class InputAdapterDocumentController {

    private final InputPortDocument inputPortDocument;

    public InputAdapterDocumentController(InputPortDocument inputPortDocument) {
        this.inputPortDocument = inputPortDocument;
    }

    @PostMapping
    public ResponseEntity<byte[]> createDocument(@RequestBody DocumentRequest documentRequest) throws Exception {
        var result = inputPortDocument.generate(documentRequest);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=doc.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(result);
    }

}