package com.form_builder.backend_forms_fer.forms.service;


import com.form_builder.backend_forms_fer.forms.model.shared.DocumentBuildRequestTemplate;
import org.apache.poi.xwpf.usermodel.*;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Service
public class DocumentBuildService {

    public byte[] generateWordDocument(String templatePath, DocumentBuildRequestTemplate[] requestTemplate) throws IOException {
        try (InputStream templateStream = Files.newInputStream(Path.of(templatePath));
             XWPFDocument document = new XWPFDocument(templateStream)) {

            // Procesar todos los párrafos del documento
            processParagraphs(document.getParagraphs(), requestTemplate);

            // Procesar tablas en el documento (incluyen párrafos dentro de celdas)
            for (XWPFTable table : document.getTables()) {
                for (XWPFTableRow row : table.getRows()) {
                    for (XWPFTableCell cell : row.getTableCells()) {
                        processParagraphs(cell.getParagraphs(), requestTemplate);
                    }
                }
            }

            // Convertir el documento a un array de bytes
            try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
                document.write(outputStream);
                return outputStream.toByteArray();
            }
        }
    }

    // Método para procesar y reemplazar texto en una lista de párrafos
    private void processParagraphs(List<XWPFParagraph> paragraphs, DocumentBuildRequestTemplate[] requestTemplate) {
        for (XWPFParagraph paragraph : paragraphs) {
            List<XWPFRun> runs = paragraph.getRuns();
            if (runs != null) {
                StringBuilder paragraphText = new StringBuilder();

                // Combinar todos los runs en el párrafo
                for (XWPFRun run : runs) {
                    String text = run.getText(0);
                    if (text != null) {
                        paragraphText.append(text);
                    }
                }

                String replacedText = paragraphText.toString();

                // Reemplazar campos en el texto combinado
                for (DocumentBuildRequestTemplate template : requestTemplate) {
                    replacedText = replacedText.replace(template.getField(), template.getValue());
                }

                // Si hay cambios, reconstruir los runs del párrafo
                if (!replacedText.contentEquals(paragraphText)) {
                    // Eliminar los runs actuales
                    for (int i = runs.size() - 1; i >= 0; i--) {
                        paragraph.removeRun(i);
                    }

                    // Crear nuevos runs con el texto actualizado
                    String[] lines = replacedText.split("\n");
                    for (String line : lines) {
                        XWPFRun newRun = paragraph.createRun();
                        newRun.setText(line, 0);
                        newRun.addCarriageReturn(); // Mantener los saltos de línea
                    }
                }
            }
        }
    }

}
