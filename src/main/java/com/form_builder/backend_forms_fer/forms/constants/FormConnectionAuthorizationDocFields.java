package com.form_builder.backend_forms_fer.forms.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum FormConnectionAuthorizationDocFields {
    DOC_PATH("src/main/resources/files/AUT_CONEXION-236.docx"),
    FIELD_DATE("${date}"),
    FIELD_FROM_NAME("${fromName}"),
    FIELD_FROM_NAME_2("${fromName2}"),
    FIELD_FROM_PHONE("${fromPhone}"),
    FIELD_FROM_IDENTIFICATION("${fromIdentification}"),
    FIELD_FROM_IDENTIFICATION_2("${fromIdentification2}"),
    FIELD_FROM_LOCATION("${fromLocation}"),
    FIELD_FROM_ADDRESS("${fromAddress}"),
    FIELD_STRUCTURE_CODE("${structureCode}"),
    FIELD_STRUCTURE_LOCATION("${structureLocation}"),
    FIELD_TO_NAME("${toName}"),
    FIELD_TO_IDENTIFICATION("${toIdentification}"),
    FIELD_TO_LOCATION("${toLocation}");

    private final String value;

}
