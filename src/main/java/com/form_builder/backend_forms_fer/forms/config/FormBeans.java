package com.form_builder.backend_forms_fer.forms.config;

import com.form_builder.backend_forms_fer.forms.service.FormAuthorizationProceduresService;
import com.form_builder.backend_forms_fer.forms.service.FormConnectionAuthorizationService;
import com.form_builder.backend_forms_fer.forms.service.FormThereAreNoRoadLinesPermitsService;
import com.form_builder.backend_forms_fer.forms.service.contracts.IFormService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class FormBeans {

    @Bean
    public List<IFormService> formServices(
            FormConnectionAuthorizationService formConnectionAuthorizationService,
            FormAuthorizationProceduresService formAuthorizationProceduresService,
            FormThereAreNoRoadLinesPermitsService formThereAreNoRoadLinesPermitsService) {

        List<IFormService> services = new ArrayList<>();

        services.add(formConnectionAuthorizationService);
        services.add(formAuthorizationProceduresService);
        services.add(formThereAreNoRoadLinesPermitsService);

        return services;
    }
}
