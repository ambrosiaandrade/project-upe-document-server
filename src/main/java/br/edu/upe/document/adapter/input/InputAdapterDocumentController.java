package br.edu.upe.document.adapter.input;

import br.edu.upe.document.adapter.input.dto.DocumentRequest;
import br.edu.upe.document.domain.port.input.InputPortDocument;
import br.edu.upe.document.domain.port.input.InputPortTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/document")
public class InputAdapterDocumentController {

    private final InputPortDocument inputPortDocument;
    private final InputPortTemplate inputPortTemplate;

    public InputAdapterDocumentController(InputPortDocument inputPortDocument, InputPortTemplate inputPortTemplate) {
        this.inputPortDocument = inputPortDocument;
        this.inputPortTemplate = inputPortTemplate;
    }

    @PostMapping
    public ResponseEntity<byte[]> createDocument(@RequestBody DocumentRequest documentRequest) throws Exception {
        var result = inputPortDocument.generate(documentRequest);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=doc.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(result);
    }

    @GetMapping()
    public ResponseEntity<String> getTemplate(@RequestParam String template, @RequestParam(name = "campus_id", defaultValue = "PE001", required = false) String campus) throws Exception {
        var result = inputPortTemplate.getTemplate(template);
        return ResponseEntity.ok().body(result);
    }

}