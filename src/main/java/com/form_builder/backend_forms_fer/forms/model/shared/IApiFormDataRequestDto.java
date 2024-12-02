package com.form_builder.backend_forms_fer.forms.model.shared;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.form_builder.backend_forms_fer.forms.model.formConnectionAuthorization.FormConnectionAuthorizationDto;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Formulario a guardar")
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = FormConnectionAuthorizationDto.class, name = "FormConnectionAuthorization"),
})
public interface IApiFormDataRequestDto {
}
