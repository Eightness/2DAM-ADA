package com.dam2.CRUDStudents.utils;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppStudentConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components())
                .info(new Info().title("Students API")
                        .description("CRUD (Create, Read, Update, Delete)")
                        .contact(new Contact()
                                .name("Albert Lozano Blasco")
                                .email("albertlb08@gmail.com"))
                        .version("1.0"));
    }
}
