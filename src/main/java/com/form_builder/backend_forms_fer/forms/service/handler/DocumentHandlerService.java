package com.form_builder.backend_forms_fer.forms.service.handler;

import com.form_builder.backend_forms_fer.forms.model.shared.ApiRequestDto;
import com.form_builder.backend_forms_fer.forms.service.IDocumentBuildTemplate;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DocumentHandlerService {
    private final List<IDocumentBuildTemplate> documentBuildWordTemplates;

    public ResponseEntity<byte[]> generateDocument(ApiRequestDto requestDto) {
        return documentBuildWordTemplates.stream()
                .filter(t -> t.supports(requestDto.formData().getClass()))
                .findFirst()
                .map(t -> t.documentBuild(requestDto.id()))
                .orElseThrow(
                        () -> new IllegalArgumentException(
                                "No document build template found for form class " + requestDto.formData().getClass()
                        )
                );
    }
}
