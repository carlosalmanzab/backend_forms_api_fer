package com.form_builder.backend_forms_fer.forms.controller;

import com.form_builder.backend_forms_fer.forms.model.shared.ApiRequestDto;
import com.form_builder.backend_forms_fer.forms.model.shared.ApiResponseDto;
import com.form_builder.backend_forms_fer.forms.model.shared.IApiFormDataResponseDto;
import com.form_builder.backend_forms_fer.forms.service.IFormService;
import com.form_builder.backend_forms_fer.forms.service.handler.FormHandlerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/form")
@RequiredArgsConstructor
public class FormController {
    private final FormHandlerService formHandlerService;

    @PostMapping()
    @Operation(summary = "Guardar el formulario")
    public ResponseEntity<ApiResponseDto> save(
            @RequestBody

            @Schema(description = "Formulario a guardar", oneOf = {ApiRequestDto.class})
            final ApiRequestDto formInput) {
        return formHandlerService.save(formInput);
    }
}
