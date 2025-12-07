package br.edu.upe.document.adapter.output;

import br.edu.upe.document.adapter.input.dto.DocumentRequest;
import br.edu.upe.document.domain.port.output.OutputPortDocument;
import org.apache.poi.xwpf.usermodel.*;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

@Component
public class OutputAdapterDocumentProcessor implements OutputPortDocument {

    @Override
    public byte[] processDocument(DocumentRequest documentContent) {
        System.out.println("Processing document: " + documentContent.getDocumentType().name());

        try (InputStream template = getClass().getResourceAsStream("/templates/"
                + documentContent.getDocumentType().name() + ".docx")) {

            if (template == null) {
                throw new IllegalStateException("Template não encontrado!");
            }

            try (XWPFDocument doc = new XWPFDocument(template)) {

                // Substitui no corpo
                doc.getParagraphs().forEach(p -> replaceInParagraph(p, documentContent.getFields()));

                // Substitui nas tabelas
                for (XWPFTable table : doc.getTables()) {
                    for (XWPFTableRow row : table.getRows()) {
                        for (XWPFTableCell cell : row.getTableCells()) {
                            for (XWPFParagraph p : cell.getParagraphs()) {
                                replaceInParagraph(p, documentContent.getFields());
                            }
                        }
                    }
                }

                // Substitui em headers
                for (XWPFHeader header : doc.getHeaderList()) {
                    for (XWPFParagraph p : header.getParagraphs()) {
                        replaceInParagraph(p, documentContent.getFields());
                    }
                    for (XWPFTable table : header.getTables()) {
                        for (XWPFTableRow row : table.getRows()) {
                            for (XWPFTableCell cell : row.getTableCells()) {
                                for (XWPFParagraph p : cell.getParagraphs()) {
                                    replaceInParagraph(p, documentContent.getFields());
                                }
                            }
                        }
                    }
                }

                // Substitui em footers
                for (XWPFFooter footer : doc.getFooterList()) {
                    for (XWPFParagraph p : footer.getParagraphs()) {
                        replaceInParagraph(p, documentContent.getFields());
                    }
                    for (XWPFTable table : footer.getTables()) {
                        for (XWPFTableRow row : table.getRows()) {
                            for (XWPFTableCell cell : row.getTableCells()) {
                                for (XWPFParagraph p : cell.getParagraphs()) {
                                    replaceInParagraph(p, documentContent.getFields());
                                }
                            }
                        }
                    }
                }

                try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
                    doc.write(out);
                    return out.toByteArray();
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Error while generating the document", e);
        }
    }

    /**
     * Overrides placeholders even when they are split into multiple runs.
     * Placeholders should follow the format: (remembering that your map uses lowercase braces,
     * so we do.toUpperCase() when we search). */
    private void replaceInParagraph(XWPFParagraph paragraph, Map<String, String> fields) {
        if (paragraph == null) return;

        List<XWPFRun> runs = paragraph.getRuns();
        if (runs == null || runs.isEmpty()) return;

        // 1) Concatena o texto do parágrafo
        StringBuilder fullText = new StringBuilder();
        for (XWPFRun r : runs) {
            String t = r.getText(0);
            if (t != null) fullText.append(t);
        }
        String paragraphText = fullText.toString();
        if (paragraphText.isEmpty()) return;

        // 2) Faz as substituições no texto completo
        String replacedText = paragraphText;
        boolean anyReplacement = false;
        for (Map.Entry<String, String> entry : fields.entrySet()) {
            String placeholder = "<" + entry.getKey().toUpperCase() + ">";
            if (replacedText.contains(placeholder)) {
                replacedText = replacedText.replace(placeholder, entry.getValue() == null ? "" : entry.getValue());
                System.out.println("Replacing " + placeholder + " with " + entry.getValue());
                anyReplacement = true;
            }
        }

        if (!anyReplacement) {
            return; // nada a fazer
        }

        // 3) Remove todos os runs atuais e cria um novo run com o texto substituído
        XWPFRun firstRun = runs.get(0);
        for (int i = runs.size() - 1; i >= 0; i--) {
            paragraph.removeRun(i);
        }

        XWPFRun newRun = paragraph.createRun();
        // tenta copiar estilo básico do primeiro run
        try {
            if (firstRun.getFontFamily() != null) {
                newRun.setFontFamily(firstRun.getFontFamily());
            }
            newRun.setBold(firstRun.isBold());
            newRun.setItalic(firstRun.isItalic());
            newRun.setUnderline(firstRun.getUnderline());
            newRun.setFontSize(firstRun.getFontSize());
            newRun.setColor(firstRun.getColor());
        } catch (Exception ignored) {}

        newRun.setText(replacedText, 0);
    }

}
