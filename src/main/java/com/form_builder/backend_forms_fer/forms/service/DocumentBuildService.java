package com.form_builder.backend_forms_fer.forms.service;


import com.form_builder.backend_forms_fer.forms.model.shared.DocumentBuildRequestTemplate;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class DocumentBuildService {
    public byte[] generateWordDocument(String templatePath, DocumentBuildRequestTemplate[] requestTemplate) throws IOException {
        try (InputStream templateStream = Files.newInputStream(Path.of(templatePath));
             XWPFDocument document = new XWPFDocument(templateStream)) {
            for (XWPFParagraph paragraph : document.getParagraphs()) {
                for (XWPFRun run : paragraph.getRuns()) {
                    String text = run.getText(0);
                    if (text != null) {
                        for (DocumentBuildRequestTemplate template : requestTemplate) {
                            text = text.replace(template.getField(), template.getValue());
                        }
                        run.setText(text, 0);
                    }
                }
            }

            try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
                document.write(outputStream);
                return outputStream.toByteArray();
            }
        }
    }

}
