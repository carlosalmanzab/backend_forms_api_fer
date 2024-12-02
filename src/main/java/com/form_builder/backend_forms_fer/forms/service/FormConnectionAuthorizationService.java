package com.form_builder.backend_forms_fer.forms.service;

import com.form_builder.backend_forms_fer.forms.constants.FormConnectionAuthorizationDocFields;
import com.form_builder.backend_forms_fer.forms.model.formConnectionAuthorization.FormConnectionAuthoriczationJpaEntity;
import com.form_builder.backend_forms_fer.forms.model.formConnectionAuthorization.FormConnectionAuthorizationDto;
import com.form_builder.backend_forms_fer.forms.model.formConnectionAuthorization.FormConnectionAuthorizationMapper;
import com.form_builder.backend_forms_fer.forms.model.shared.*;
import com.form_builder.backend_forms_fer.forms.repository.FormConnectionAuthorizationRepository;
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
public class FormConnectionAuthorizationService
        implements IFormService, IDocumentBuildTemplate {

    private final FormConnectionAuthorizationMapper mapper;
    private final FormConnectionAuthorizationRepository repository;
    private final DocumentBuildService documentBuilderService;

    @Override
    public ResponseEntity<byte[]> documentBuild(Long id) {
        FormConnectionAuthoriczationJpaEntity entity = repository.findById(id).orElseThrow(() -> new RuntimeException("Form not found with ID: " + id));

        DocumentBuildRequestTemplate[] templates = Arrays.stream(FormConnectionAuthorizationDocFields.values())
                .map(field -> new DocumentBuildRequestTemplate(field.getValue(), getFieldValue(entity, field)))
                .toArray(DocumentBuildRequestTemplate[]::new);
        byte[] document = null;
        try {
            document = documentBuilderService.generateWordDocument(FormConnectionAuthorizationDocFields.DOC_PATH.getValue(), templates);

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
                .map(formData -> (FormConnectionAuthorizationDto) formData)
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
        Optional<FormConnectionAuthoriczationJpaEntity> existingFormOptional = repository.findById(requestDto.id());
        if (existingFormOptional.isPresent()) {
            FormConnectionAuthoriczationJpaEntity existingForm;
            existingForm = mapper.toEntity((FormConnectionAuthorizationDto) requestDto.formData());
            existingForm.setId(requestDto.id());
            existingForm.setUpdatedDate(new Date());
            FormConnectionAuthoriczationJpaEntity savedEntity = repository.save(existingForm);
            ApiResponseDto response = mapper.toApiResponseDto(savedEntity);
            return ResponseEntity.ok(response);
        } else {
            throw new EntityNotFoundException("Form not found with ID: " + requestDto.id());
        }
    }

    @Override
    public ResponseEntity<ApiResponseDto> findById(ApiRequestDto requestDto) {
        Optional<FormConnectionAuthoriczationJpaEntity> existingFormOptional = repository.findById(requestDto.id());
        if (existingFormOptional.isPresent()) {
            FormConnectionAuthoriczationJpaEntity existingForm = existingFormOptional.get();
            ApiResponseDto response = mapper.toApiResponseDto(existingForm);
            return ResponseEntity.ok(response);
        } else {
            throw new EntityNotFoundException("Form not found with ID: " + requestDto.id());
        }
    }

    @Override
    public boolean supports(Class<? extends IApiFormDataRequestDto> formClass) {
        return FormConnectionAuthorizationDto.class.equals(formClass);
    }

    private String getFieldValue(FormConnectionAuthoriczationJpaEntity entity, FormConnectionAuthorizationDocFields field) {
        return switch (field) {
            case FIELD_DATE -> entity.getDate();
            case FIELD_FROM_NAME -> entity.getFromName();
            case FIELD_FROM_IDENTIFICATION -> entity.getFromIdentification();
            case FIELD_FROM_LOCATION -> entity.getFromLocation();
            case FIELD_STRUCTURE_CODE -> entity.getStructureCode();
            case FIELD_STRUCTURE_LOCATION -> entity.getStructureLocation();
            case FIELD_TO_NAME -> entity.getToName();
            case FIELD_TO_IDENTIFICATION -> entity.getToIdentification();
            case FIELD_TO_LOCATION -> entity.getToLocation();
            case DOC_PATH -> FormConnectionAuthorizationDocFields.DOC_PATH.getValue();
            default -> throw new RuntimeException("Unhandled field: " + field);
        };
    }

}
