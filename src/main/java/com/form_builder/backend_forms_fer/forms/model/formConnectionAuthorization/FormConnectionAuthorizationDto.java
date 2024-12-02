package com.form_builder.backend_forms_fer.forms.model.formConnectionAuthorization;

import com.form_builder.backend_forms_fer.forms.model.shared.IApiFormDataRequestDto;
import com.form_builder.backend_forms_fer.forms.model.shared.IApiFormDataResponseDto;

public record FormConnectionAuthorizationDto (
        String date,
        String fromName,
        String fromIdentification,
        String fromLocation,
        String fromPhone,
        String structureCode,
        String structureLocation,
        String toName,
        String toIdentification,
        String toLocation
        ) implements IApiFormDataRequestDto, IApiFormDataResponseDto { }
