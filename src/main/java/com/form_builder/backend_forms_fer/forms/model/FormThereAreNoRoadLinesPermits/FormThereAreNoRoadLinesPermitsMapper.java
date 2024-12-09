package com.form_builder.backend_forms_fer.forms.model.FormThereAreNoRoadLinesPermits;

import com.form_builder.backend_forms_fer.forms.model.shared.ApiResponseDto;
import com.form_builder.backend_forms_fer.forms.model.shared.IApiFormDataResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.LinkedHashMap;

@Mapper(componentModel = "spring")
public interface FormThereAreNoRoadLinesPermitsMapper {
    FormThereAreNoRoadLinesPermitsDto toDto(FormThereAreNoRoadLinesPermitsJpaEntity entity);

    @Mapping(target = "date", source = "date")
    @Mapping(target = "fromAddress", source = "fromAddress")
    @Mapping(target = "fromName", source = "fromName")
    @Mapping(target = "fromIdentification", source = "fromIdentification")
    @Mapping(target = "fromPhone", source = "fromPhone")
    FormThereAreNoRoadLinesPermitsJpaEntity toEntity(LinkedHashMap<String, String> hashMap);
    FormThereAreNoRoadLinesPermitsJpaEntity toEntity(FormThereAreNoRoadLinesPermitsDto dto);

    @Mapping(target = "id", source = "entity.id")
    @Mapping(target = "formData", source = "entity")
    @Mapping(target = "createdDate", source = "entity.createdDate")
    @Mapping(target = "updatedDate", source = "entity.updatedDate")
    @Mapping(target = "paymentStatus", source = "entity.paymentStatus")
    @Mapping(target = "formType", expression = "java(\"FormThereAreNoRoadLinesPermits\")")
    default ApiResponseDto toApiResponseDto(FormThereAreNoRoadLinesPermitsJpaEntity entity) {
        IApiFormDataResponseDto formData = toDto(entity);
        return new ApiResponseDto(
                entity.getId(),
                formData,
                entity.getCreatedDate(),
                entity.getUpdatedDate(),
                entity.getPaymentStatus()
        );
    }

}
