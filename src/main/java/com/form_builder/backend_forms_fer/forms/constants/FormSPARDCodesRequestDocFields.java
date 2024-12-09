package com.form_builder.backend_forms_fer.forms.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum FormSPARDCodesRequestDocFields {
    DOC_PATH("src/main/resources/files/SOLICITUD DE CODIGOS-240.docx"),
    FIELD_FROM_ADDRESS("${fromAddress}"),
    FIELD_FROM_NAME("${fromName}"),
    FIELD_FROM_IDENTIFICATION("${fromIdentification}"),
    FIELD_DATE("${date}"),
    FIELD_PROJECT_LOCATION("${projectLocation}"),
    FIELD_PROJECT_ADDRESS("${projectAddress}"),
    FIELD_USER_NAME("${userName}"),
    FIELD_USER_IDENTIFICATION("${userIdentification}"),
    FIELD_USER_LOCATION("${userLocation}"),
    FIELD_FROM_NAME_2("${fromName2}"),
    FIELD_FROM_IDENTIFICATION_2("${fromIdentification2}"),
    FIELD_FROM_PHONE("${fromPhone}"),
    FIELD_FROM_EMAIL("${fromEmail}");

    private final String value;
}
