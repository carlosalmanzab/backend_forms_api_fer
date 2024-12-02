package com.form_builder.backend_forms_fer;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Backend Forms API", version = "1.0", description = "Forms management API"))
@ComponentScan(basePackages = {"com.form_builder.backend_forms_fer", "com.form_builder.backend_forms_fer.forms"})
public class BackendFormsFerApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendFormsFerApplication.class, args);
	}

}
