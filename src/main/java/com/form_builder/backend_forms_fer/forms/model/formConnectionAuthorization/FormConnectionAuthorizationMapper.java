package com.form_builder.backend_forms_fer.forms.model.formConnectionAuthorization;

import com.form_builder.backend_forms_fer.forms.model.shared.ApiResponseDto;
import com.form_builder.backend_forms_fer.forms.model.shared.IApiFormDataResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.LinkedHashMap;

@Mapper(componentModel = "spring")
public interface FormConnectionAuthorizationMapper {

    //FormConnectionAuthorizationMapper INSTANCE = Mappers.getMapper(FormConnectionAuthorizationMapper.class);

    FormConnectionAuthorizationDto toDto(FormConnectionAuthoriczationJpaEntity entity);


    @Mapping(target = "date", source = "date")
    @Mapping(target = "fromName", source = "fromName")
    @Mapping(target = "fromIdentification", source = "fromIdentification")
    @Mapping(target = "fromLocation", source = "fromLocation")
    @Mapping(target = "fromPhone", source = "fromPhone")
    @Mapping(target = "structureCode", source = "structureCode")
    @Mapping(target = "structureLocation", source = "structureLocation")
    @Mapping(target = "toName", source = "toName")
    @Mapping(target = "toIdentification", source = "toIdentification")
    @Mapping(target = "toLocation", source = "toLocation")
    FormConnectionAuthoriczationJpaEntity toEntity(LinkedHashMap<String, String> hashMap);
    FormConnectionAuthoriczationJpaEntity toEntity(FormConnectionAuthorizationDto dto);

    @Mapping(target = "id", source = "entity.id")
    @Mapping(target = "formData", source = "entity")
    @Mapping(target = "createdDate", source = "entity.createdDate")
    @Mapping(target = "updatedDate", source = "entity.updatedDate")
    @Mapping(target = "paymentStatus", source = "entity.paymentStatus")
    @Mapping(target = "formType", expression = "java(\"FormConnectionAuthorization\")")
    default ApiResponseDto toApiResponseDto(FormConnectionAuthoriczationJpaEntity entity) {
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
