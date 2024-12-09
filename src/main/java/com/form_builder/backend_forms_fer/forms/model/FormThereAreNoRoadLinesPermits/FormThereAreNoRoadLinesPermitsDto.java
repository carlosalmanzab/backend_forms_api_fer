package com.form_builder.backend_forms_fer.forms.model.FormThereAreNoRoadLinesPermits;

import com.form_builder.backend_forms_fer.forms.model.shared.IApiFormDataRequestDto;
import com.form_builder.backend_forms_fer.forms.model.shared.IApiFormDataResponseDto;

public record FormThereAreNoRoadLinesPermitsDto(
        String date,
        String fromAddress,
        String fromName,
        String fromIdentification,
        String fromPhone
) implements IApiFormDataRequestDto, IApiFormDataResponseDto {
}
