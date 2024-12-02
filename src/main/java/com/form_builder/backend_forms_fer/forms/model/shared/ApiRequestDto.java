package com.form_builder.backend_forms_fer.forms.model.shared;

import com.form_builder.backend_forms_fer.forms.model.formConnectionAuthorization.FormConnectionAuthorizationDto;
import io.swagger.v3.oas.annotations.media.Schema;

public record ApiRequestDto(
        @Schema(description = "ID de la respuesta", example = "12345")
        Long id,
        @Schema(description = "Datos de la respuesta que pueden ser de cualquier tipo", oneOf = {FormConnectionAuthorizationDto.class})
        IApiFormDataRequestDto formData
) {
}
