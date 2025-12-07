package br.edu.upe.document.adapter.output;

import br.edu.upe.document.adapter.input.dto.DocumentRequest;
import br.edu.upe.document.domain.port.output.OutputPortDocument;
import org.apache.poi.xwpf.usermodel.*;
import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

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

                // 1. Substituições (Corpo)
                doc.getParagraphs().forEach(p -> replaceInParagraph(p, documentContent.getFields()));

                // 2. Substituições (Tabelas)
                for (XWPFTable table : doc.getTables()) {
                    for (XWPFTableRow row : table.getRows()) {
                        for (XWPFTableCell cell : row.getTableCells()) {
                            for (XWPFParagraph p : cell.getParagraphs()) {
                                replaceInParagraph(p, documentContent.getFields());
                            }
                        }
                    }
                }

                // 3. Substituições (Headers - Cabeçalhos)
                for (XWPFHeader header : doc.getHeaderList()) {
                    for (XWPFParagraph p : header.getParagraphs()) {
                        replaceInParagraph(p, documentContent.getFields());
                    }
                    for (XWPFTable table : header.getTables()) {
                        replaceInTable(table, documentContent.getFields());
                    }
                }

                // 4. Substituições (Footers - Rodapés)
                for (XWPFFooter footer : doc.getFooterList()) {
                    for (XWPFParagraph p : footer.getParagraphs()) {
                        replaceInParagraph(p, documentContent.getFields());
                    }
                    for (XWPFTable table : footer.getTables()) {
                        replaceInTable(table, documentContent.getFields());
                    }
                }

                // Gera o DOCX em memória
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                doc.write(out);
                byte[] docxBytes = out.toByteArray();

                // 5. CONVERTE PARA PDF
                // Se o frontend esperar PDF, retornamos o PDF.
                return convertToPdf(docxBytes);
            }
        } catch (Exception e) {
            throw new RuntimeException("Error while generating the document", e);
        }
    }

    /**
     * Método auxiliar para tabelas (evita repetição de código)
     */
    private void replaceInTable(XWPFTable table, Map<String, String> fields) {
        for (XWPFTableRow row : table.getRows()) {
            for (XWPFTableCell cell : row.getTableCells()) {
                for (XWPFParagraph p : cell.getParagraphs()) {
                    replaceInParagraph(p, fields);
                }
            }
        }
    }

    /**
     * Lógica de substituição mantendo a formatação original (Arial/Calibri/etc).
     */
    private void replaceInParagraph(XWPFParagraph paragraph, Map<String, String> fields) {
        if (paragraph == null || paragraph.getRuns() == null || paragraph.getRuns().isEmpty()) return;

        List<XWPFRun> runs = paragraph.getRuns();

        // Reconstrói texto
        StringBuilder fullText = new StringBuilder();
        for (XWPFRun r : runs) {
            String t = r.getText(0);
            if (t != null) fullText.append(t);
        }

        String paragraphText = fullText.toString();
        if (paragraphText.isEmpty()) return;

        String replacedText = paragraphText;
        boolean anyReplacement = false;

        for (Map.Entry<String, String> entry : fields.entrySet()) {
            String placeholder = "<" + entry.getKey().toUpperCase() + ">";
            if (replacedText.contains(placeholder)) {
                String valor = entry.getValue() == null ? "" : entry.getValue();
                replacedText = replacedText.replace(placeholder, valor);
                anyReplacement = true;
            }
        }

        if (!anyReplacement) return;

        // --- PRESERVAÇÃO DE ESTILO ---
        XWPFRun styleSource = runs.get(0);
        String fontFamily = styleSource.getFontFamily();
        Double fontSize = styleSource.getFontSizeAsDouble();
        boolean isBold = styleSource.isBold();
        boolean isItalic = styleSource.isItalic();
        UnderlinePatterns underline = styleSource.getUnderline();
        String color = styleSource.getColor();

        // Remove runs antigos
        for (int i = runs.size() - 1; i >= 0; i--) {
            paragraph.removeRun(i);
        }

        // Cria novo run com estilo
        XWPFRun newRun = paragraph.createRun();
        newRun.setText(replacedText);

        if (fontFamily != null) newRun.setFontFamily(fontFamily);
        if (fontSize != null) newRun.setFontSize(fontSize);
        newRun.setBold(isBold);
        newRun.setItalic(isItalic);
        newRun.setUnderline(underline);
        if (color != null) newRun.setColor(color);
    }

    // ==================================================================================
    // LÓGICA DE CONVERSÃO PDF (CROSS-PLATFORM)
    // ==================================================================================

    private byte[] convertToPdf(byte[] docxBytes) throws IOException, InterruptedException {
        Path tempDocx = Files.createTempFile("upe_doc_", ".docx");
        Path tempPdf = null;

        try {
            Files.write(tempDocx, docxBytes);
            Path outputDir = tempDocx.getParent();

            // Detecta o comando correto baseado no OS
            String libreOfficeCommand = getLibreOfficeCommand();

            ProcessBuilder processBuilder = new ProcessBuilder(
                    libreOfficeCommand,
                    "--headless",
                    "--convert-to", "pdf",
                    tempDocx.toAbsolutePath().toString(),
                    "--outdir", outputDir.toAbsolutePath().toString()
            );

            Process process = processBuilder.start();
            boolean finished = process.waitFor(30, TimeUnit.SECONDS);

            if (!finished || process.exitValue() != 0) {
                // Tenta ler o erro para debug
                String errorMsg = new String(process.getErrorStream().readAllBytes());
                throw new RuntimeException("Erro na conversão PDF. LibreOffice instalado? Log: " + errorMsg);
            }

            // O LibreOffice cria o PDF com o mesmo nome base
            String pdfFileName = tempDocx.getFileName().toString().replace(".docx", ".pdf");
            tempPdf = outputDir.resolve(pdfFileName);

            if (!Files.exists(tempPdf)) {
                throw new RuntimeException("Arquivo PDF não foi gerado no caminho esperado: " + tempPdf);
            }

            return Files.readAllBytes(tempPdf);

        } finally {
            // Limpeza
            Files.deleteIfExists(tempDocx);
            if (tempPdf != null) Files.deleteIfExists(tempPdf);
        }
    }

    private String getLibreOfficeCommand() {
        String os = System.getProperty("os.name").toLowerCase();

        if (os.contains("win")) {
            // Caminho comum no Windows. Se os devs instalarem em outro lugar,
            // precisarão adicionar ao PATH do sistema ou ajustar aqui.
            // Opção A: Tentar achar no PATH
            // Opção B: Caminho fixo (mais arriscado)

            // Vamos tentar o comando 'soffice' assumindo que está no PATH (recomendado)
            return "soffice";
        } else if (os.contains("nix") || os.contains("nux") || os.contains("mac")) {
            return "libreoffice"; // Padrão Linux/Mac
        }

        return "libreoffice";
    }
}