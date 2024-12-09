package com.form_builder.backend_forms_fer.forms.model.formAuthorizationProcedures;

import com.form_builder.backend_forms_fer.forms.model.shared.ApiResponseDto;
import com.form_builder.backend_forms_fer.forms.model.shared.IApiFormDataResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.LinkedHashMap;

@Mapper(componentModel = "spring")
public interface FormAuthorizationProceduresMapper {

    FormAuthorizationProceduresDto toDto(FormAuthorizationProceduresJpaEntity entity);
    FormAuthorizationProceduresJpaEntity toEntity(LinkedHashMap<String, String> hashMap);
    FormAuthorizationProceduresJpaEntity toEntity(FormAuthorizationProceduresDto dto);

    @Mapping(target = "id", source = "entity.id")
    @Mapping(target = "formData", source = "entity")
    @Mapping(target = "createdDate", source = "entity.createdDate")
    @Mapping(target = "updatedDate", source = "entity.updatedDate")
    @Mapping(target = "paymentStatus", source = "entity.paymentStatus")
    @Mapping(target = "formType", expression = "java(\"FormAuthorizationProcedures\")")
    default ApiResponseDto toApiResponseDto(FormAuthorizationProceduresJpaEntity entity) {
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
