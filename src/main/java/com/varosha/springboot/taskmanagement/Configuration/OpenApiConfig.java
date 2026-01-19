package com.varosha.springboot.taskmanagement.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.tags.Tag;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        final String securitySchemeName = "bearerAuth";
        Contact contact = new Contact();
        contact.setName("Sandeep Mainali");
        contact.setEmail("sawondeep4@gmail.com");
        return new OpenAPI()
                .info(new Info()
                        .title("Task Management API")
                        .version("1.0")
                        .description("API documentation for the Task Management System")
                        .contact(contact))
                .addTagsItem(new Tag().name("Authentication").description("Log-in using your credentials to get token."))
                .addTagsItem(new Tag().name("General Employee Panel").description("All employees may read their profiles and tasks and submit Standup."))
                .addTagsItem(new Tag().name("Notification Dashboard").description(""))
                .addTagsItem(new Tag().name("Admin Employee panel").description("Authorized admins may perform CRUD operations on Users profiles."))
                .addTagsItem(new Tag().name("Admin Task panel").description("Admin my create and assign it to employees and read tasks"))
                .addSecurityItem(new SecurityRequirement().addList(securitySchemeName))
                .components(new Components()
                        .addSecuritySchemes(securitySchemeName, new SecurityScheme()
                                .name(securitySchemeName)
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")));
    }


}