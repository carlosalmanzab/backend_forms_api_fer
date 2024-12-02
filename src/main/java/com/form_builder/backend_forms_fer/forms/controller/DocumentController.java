package com.form_builder.backend_forms_fer.forms.controller;

import com.form_builder.backend_forms_fer.forms.model.shared.ApiRequestDto;
import com.form_builder.backend_forms_fer.forms.service.handler.DocumentHandlerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/document")
@RequiredArgsConstructor
public class DocumentController {
    private final DocumentHandlerService documentHandlerService;

    @GetMapping()
    @Operation(summary = "Generar documento")
    public ResponseEntity<byte[]> generateDocument(
            @RequestBody
            @Schema(description = "Formulario a guardar", oneOf = {ApiRequestDto.class})
            final ApiRequestDto requestDto) {
        return documentHandlerService.generateDocument(requestDto);
    }
}
