package com.form_builder.backend_forms_fer.forms.model.shared;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class DocumentBuildRequestTemplate {
    public String field;
    public String value;
}
