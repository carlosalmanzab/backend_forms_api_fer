package com.form_builder.backend_forms_fer.configguration.swagger;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.security.SecurityScheme.Type;
import io.swagger.v3.oas.models.Components;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    OpenAPI openAPI() {
        var securitySchemeName = "bearerAuth";

        var openApi = new OpenAPI().info(new Info()
                .title("RESTful API with Spring Boot")
                .version("v1")
                .description("")
                .termsOfService("url github")
                .license(new License()
                        .name("Apache 2.0")
                        .url("url de github")));

        openApi.setComponents(new Components());

        openApi.getComponents()
                .addSecuritySchemes(securitySchemeName,
                        new SecurityScheme().type(Type.HTTP).scheme("bearer").bearerFormat("JWT"));

        var securityRequirement = new SecurityRequirement().addList(securitySchemeName);

        openApi.addSecurityItem(securityRequirement);

        return openApi;
    }
}
