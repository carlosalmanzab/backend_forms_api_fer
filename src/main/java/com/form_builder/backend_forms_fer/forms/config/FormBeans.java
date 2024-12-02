package com.form_builder.backend_forms_fer.forms.config;

import com.form_builder.backend_forms_fer.forms.model.formConnectionAuthorization.FormConnectionAuthorizationMapper;
import com.form_builder.backend_forms_fer.forms.repository.FormConnectionAuthorizationRepository;
import com.form_builder.backend_forms_fer.forms.service.DocumentBuildService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.form_builder.backend_forms_fer.forms.service.FormConnectionAuthorizationService;

import java.util.ArrayList;
import java.util.List;
import com.form_builder.backend_forms_fer.forms.service.IFormService;
import com.form_builder.backend_forms_fer.forms.model.shared.IApiFormDataRequestDto;
import com.form_builder.backend_forms_fer.forms.model.shared.IApiFormDataResponseDto;

@Configuration
@RequiredArgsConstructor
public class FormBeans {
    private final FormConnectionAuthorizationMapper mapper;
    private final FormConnectionAuthorizationRepository repository;
    private final DocumentBuildService documentBuilderService;


    @Bean
    public List<IFormService> formServices() {
        List<IFormService> services = new ArrayList<>();
        services.add(new FormConnectionAuthorizationService(mapper, repository, documentBuilderService));
        return services;
    }
}
