package com.form_builder.backend_forms_fer.forms.service;

import com.form_builder.backend_forms_fer.forms.constants.FormThereAreNoRoadLinesPermitsDocFields;
import com.form_builder.backend_forms_fer.forms.model.FormThereAreNoRoadLinesPermits.FormThereAreNoRoadLinesPermitsDto;
import com.form_builder.backend_forms_fer.forms.model.FormThereAreNoRoadLinesPermits.FormThereAreNoRoadLinesPermitsJpaEntity;
import com.form_builder.backend_forms_fer.forms.model.FormThereAreNoRoadLinesPermits.FormThereAreNoRoadLinesPermitsMapper;
import com.form_builder.backend_forms_fer.forms.model.shared.*;
import com.form_builder.backend_forms_fer.forms.repository.FormThereAreNoRoadLinesPermitsRepository;
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
public class FormThereAreNoRoadLinesPermitsService implements IFormService, IDocumentBuildTemplate {
    private final FormThereAreNoRoadLinesPermitsMapper mapper;
    private final FormThereAreNoRoadLinesPermitsRepository repository;
    private final DocumentBuildService documentBuilderService;

    @Override
    public ResponseEntity<byte[]> documentBuild(Long id) {
        FormThereAreNoRoadLinesPermitsJpaEntity entity = repository.findById(id).orElseThrow(() -> new RuntimeException("Form not found with ID: " + id));

        DocumentBuildRequestTemplate[] templates = Arrays.stream(FormThereAreNoRoadLinesPermitsDocFields.values())
                .map(field -> new DocumentBuildRequestTemplate(field.getValue(), getFieldValue(entity, field)))
                .toArray(DocumentBuildRequestTemplate[]::new);
        byte[] document;
        try {
            document = documentBuilderService.generateWordDocument(FormThereAreNoRoadLinesPermitsDocFields.DOC_PATH.getValue(), templates);

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
                .map(formData -> (FormThereAreNoRoadLinesPermitsDto) formData)
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
        Optional<FormThereAreNoRoadLinesPermitsJpaEntity> existingFormOptional = repository.findById(requestDto.id());
        if (existingFormOptional.isPresent()) {
            FormThereAreNoRoadLinesPermitsJpaEntity existingForm;
            existingForm = mapper.toEntity((FormThereAreNoRoadLinesPermitsDto) requestDto.formData());
            existingForm.setId(requestDto.id());
            existingForm.setUpdatedDate(new Date());
            FormThereAreNoRoadLinesPermitsJpaEntity savedEntity = repository.save(existingForm);
            ApiResponseDto response = mapper.toApiResponseDto(savedEntity);
            return ResponseEntity.ok(response);
        } else {
            throw new EntityNotFoundException("Form not found with ID: " + requestDto.id());
        }
    }

    @Override
    public ResponseEntity<ApiResponseDto> findById(ApiRequestDto requestDto) {
        Optional<FormThereAreNoRoadLinesPermitsJpaEntity> existingFormOptional = repository.findById(requestDto.id());
        if (existingFormOptional.isPresent()) {
            FormThereAreNoRoadLinesPermitsJpaEntity existingForm = existingFormOptional.get();
            ApiResponseDto response = mapper.toApiResponseDto(existingForm);
            return ResponseEntity.ok(response);
        } else {
            throw new EntityNotFoundException("Form not found with ID: " + requestDto.id());
        }
    }

    @Override
    public boolean supports(Class<? extends IApiFormDataRequestDto> formClass) {
        return FormThereAreNoRoadLinesPermitsDto.class.equals(formClass);
    }

    private String getFieldValue(FormThereAreNoRoadLinesPermitsJpaEntity entity, FormThereAreNoRoadLinesPermitsDocFields field) {
       return switch (field) {
            case FIELD_DATE -> entity.getDate();
            case FIELD_FROM_ADDRESS -> entity.getFromAddress();
            case FIELD_FROM_NAME -> entity.getFromName();
            case FIELD_FROM_IDENTIFICATION -> entity.getFromIdentification();
            case FIELD_FROM_PHONE -> entity.getFromPhone();
            case DOC_PATH -> FormThereAreNoRoadLinesPermitsDocFields.DOC_PATH.getValue();
        };
    }

}
