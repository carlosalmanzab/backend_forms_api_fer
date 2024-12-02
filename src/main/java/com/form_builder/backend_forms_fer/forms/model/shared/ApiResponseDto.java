package com.form_builder.backend_forms_fer.forms.model.shared;

import com.form_builder.backend_forms_fer.forms.model.formConnectionAuthorization.FormConnectionAuthorizationDto;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Date;

@Schema(description = "DTO de respuesta genérica con datos de formulario")
public record ApiResponseDto(
        @Schema(description = "ID de la respuesta", example = "12345")
        Long id,
        @Schema(description = "Datos de la respuesta que pueden ser de cualquier tipo", oneOf = {FormConnectionAuthorizationDto.class})
        IApiFormDataResponseDto formData,
        @Schema(description = "Fecha de creación de la respuesta", example = "2023-01-01T00:00:00.000Z")
        Date createdDate,
        @Schema(description = "Fecha de actualización de la respuesta", example = "2023-01-01T00:00:00.000Z")
        Date updatedDate,
        @Schema(description = "Estado de pago de la respuesta", example = "PAID")
        FormPaymentStatus paymentStatus) {

}

