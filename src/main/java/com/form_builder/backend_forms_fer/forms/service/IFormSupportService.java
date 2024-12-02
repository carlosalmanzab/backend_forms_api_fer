package com.form_builder.backend_forms_fer.forms.service;

import com.form_builder.backend_forms_fer.forms.model.shared.IApiFormDataRequestDto;

public interface IFormSupportService {
    /**
     * Determines if the given form class is supported by this service.
     *
     * @param formClass the class of the form data request to check support for
     * @return true if the form class is supported, false otherwise
     */
    boolean supports(Class<? extends IApiFormDataRequestDto> formClass);
}
