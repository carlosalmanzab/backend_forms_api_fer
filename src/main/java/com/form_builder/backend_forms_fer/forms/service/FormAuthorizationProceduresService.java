package com.form_builder.backend_forms_fer.forms.service;

import com.form_builder.backend_forms_fer.forms.constants.FormAuthorizationProceduresDocFields;
import com.form_builder.backend_forms_fer.forms.model.formAuthorizationProcedures.FormAuthorizationProceduresDto;
import com.form_builder.backend_forms_fer.forms.model.formAuthorizationProcedures.FormAuthorizationProceduresJpaEntity;
import com.form_builder.backend_forms_fer.forms.model.formAuthorizationProcedures.FormAuthorizationProceduresMapper;
import com.form_builder.backend_forms_fer.forms.model.shared.*;
import com.form_builder.backend_forms_fer.forms.repository.FormAuthorizationProceduresRepository;
import com.form_builder.backend_forms_fer.forms.service.contracts.IDocumentBuildTemplate;
import com.form_builder.backend_forms_fer.forms.service.contracts.IFormService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FormAuthorizationProceduresService implements IFormService, IDocumentBuildTemplate  {
    private final FormAuthorizationProceduresMapper mapper;
    private final FormAuthorizationProceduresRepository repository;
    private final DocumentBuildService documentBuilderService;

    private String getFieldValue(FormAuthorizationProceduresJpaEntity entity, FormAuthorizationProceduresDocFields field) {
        return switch (field) {
            case FIELD_DATE -> entity.getDate();
            case FIELD_FROM_EMAIL -> entity.getFromEmail();
            case FIELD_FROM_NAME, FIELD_FROM_NAME_2 -> entity.getFromName();
            case FIELD_FROM_PHONE -> entity.getFromPhone();
            case FIELD_FROM_IDENTIFICATION -> entity.getFromIdentification();
            case FIELD_FROM_IDENTIFICATION_2 -> entity.getFromIdentification() + " de " + entity.getFromLocation();
            case FIELD_FROM_LOCATION -> entity.getFromLocation();
            case FIELD_FROM_ADDRESS-> entity.getFromAddress();
            case FIELD_PROJECT_NAME -> entity.getProjectName();
            case FIELD_PROJECT_LOCATION -> entity.getProjectLocation();
            case FIELD_PROJECT_ADDRESS -> entity.getProjectAddress();
            case FIELD_TO_NAME -> entity.getToName();
            case FIELD_TO_IDENTIFICATION -> entity.getToIdentification();
            case DOC_PATH -> FormAuthorizationProceduresDocFields.DOC_PATH.getValue();
        };
    }

    @Override
    public ResponseEntity<byte[]> documentBuild(Long id) {
        FormAuthorizationProceduresJpaEntity entity = repository.findById(id).orElseThrow(() -> new RuntimeException("Form not found with ID: " + id));

        DocumentBuildRequestTemplate[] templates = Arrays.stream(FormAuthorizationProceduresDocFields.values())
                .map(field -> new DocumentBuildRequestTemplate(field.getValue(), getFieldValue(entity, field)))
                .toArray(DocumentBuildRequestTemplate[]::new);
        byte[] document;
        try {
            document = documentBuilderService.generateWordDocument(FormAuthorizationProceduresDocFields.DOC_PATH.getValue(), templates);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.setContentDispositionFormData("attachment", "generated-document.docx");

            return new ResponseEntity<>(document, headers, HttpStatus.OK);
        } catch (IOException e) {
            return ResponseEntity.internalServerError().body(null);
        }

    }

    @Override
    public ResponseEntity<ApiResponseDto> save(ApiRequestDto requestDto) {
        return Optional.of(requestDto.formData())
                .map(formData -> (FormAuthorizationProceduresDto) formData)
                .map(mapper::toEntity)
                .map(entity -> {
                    entity.setCreatedDate(new Date());
                    entity.setUpdatedDate(new Date());
                    entity.setPaymentStatus(FormPaymentStatus.PENDING_PAYMENT);
                    return entity;
                })
                .map(repository::save)
                .map(mapper::toApiResponseDto)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new RuntimeException("Failed to save form data"));
    }

    @Override
    public ResponseEntity<ApiResponseDto> update(ApiRequestDto requestDto) {
        Optional<FormAuthorizationProceduresJpaEntity> existingFormOptional = repository.findById(requestDto.id());
        if (existingFormOptional.isPresent()) {
            FormAuthorizationProceduresJpaEntity existingForm;
            existingForm = mapper.toEntity((FormAuthorizationProceduresDto) requestDto.formData());
            existingForm.setId(requestDto.id());
            existingForm.setUpdatedDate(new Date());
            FormAuthorizationProceduresJpaEntity savedEntity = repository.save(existingForm);
            ApiResponseDto response = mapper.toApiResponseDto(savedEntity);
            return ResponseEntity.ok(response);
        } else {
            throw new EntityNotFoundException("Form not found with ID: " + requestDto.id());
        }
    }

    @Override
    public ResponseEntity<ApiResponseDto> findById(ApiRequestDto requestDto) {
        Optional<FormAuthorizationProceduresJpaEntity> existingFormOptional = repository.findById(requestDto.id());
        if (existingFormOptional.isPresent()) {
            FormAuthorizationProceduresJpaEntity existingForm = existingFormOptional.get();
            ApiResponseDto response = mapper.toApiResponseDto(existingForm);
            return ResponseEntity.ok(response);
        } else {
            throw new EntityNotFoundException("Form not found with ID: " + requestDto.id());
        }
    }

    @Override
    public boolean supports(Class<? extends IApiFormDataRequestDto> formClass) {
        return FormAuthorizationProceduresDto.class.equals(formClass);
    }
}
