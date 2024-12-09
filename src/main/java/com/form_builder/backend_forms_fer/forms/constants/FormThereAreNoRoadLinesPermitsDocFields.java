package com.form_builder.backend_forms_fer.forms.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum FormThereAreNoRoadLinesPermitsDocFields {
    DOC_PATH("src/main/resources/files/NO HAY PERMISO lineas carretera.docx"),
    FIELD_DATE("${date}"),
    FIELD_FROM_ADDRESS("${fromAddress}"),
    FIELD_FROM_NAME("${fromName}"),
    FIELD_FROM_IDENTIFICATION("${fromIdentification}"),
    FIELD_FROM_PHONE("${fromPhone}");

    private final String value;

}
