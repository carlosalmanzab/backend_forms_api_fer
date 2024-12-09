package com.form_builder.backend_forms_fer.forms.model.formAuthorizationProcedures;

import com.form_builder.backend_forms_fer.forms.model.shared.IApiFormDataRequestDto;
import com.form_builder.backend_forms_fer.forms.model.shared.IApiFormDataResponseDto;

public record FormAuthorizationProceduresDto(
         String fromAddress,
         String date,
         String fromName,
         String fromIdentification,
         String fromLocation,
         String toName,
         String toIdentification,
         String projectName,
         String projectLocation,
         String projectAddress,
         String fromPhone,
         String fromEmail
) implements IApiFormDataRequestDto, IApiFormDataResponseDto {
}
