package com.form_builder.backend_forms_fer.forms.service.handler;

import com.form_builder.backend_forms_fer.forms.model.shared.ApiRequestDto;
import com.form_builder.backend_forms_fer.forms.model.shared.ApiResponseDto;
import com.form_builder.backend_forms_fer.forms.model.shared.IApiFormDataRequestDto;
import com.form_builder.backend_forms_fer.forms.model.shared.IApiFormDataResponseDto;
import com.form_builder.backend_forms_fer.forms.service.IFormCRUDService;
import com.form_builder.backend_forms_fer.forms.service.IFormService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FormHandlerService implements IFormCRUDService {

    private final List<IFormService> formServices;

    @Override
    public ResponseEntity<ApiResponseDto> save(ApiRequestDto formInput) {
        return formServices.stream()
                .filter(s -> s.supports(formInput.formData().getClass()))
                .findFirst()
                .map(s -> s.save(formInput))
                .orElseThrow(() -> new RuntimeException("No form service found for form class " + formInput.getClass()));
    }

    @Override
    public ResponseEntity<ApiResponseDto> update(ApiRequestDto requestDto) {
        return null;
    }

    @Override
    public ResponseEntity<ApiResponseDto> findById(ApiRequestDto requestDto) {
        return null;
    }

}
