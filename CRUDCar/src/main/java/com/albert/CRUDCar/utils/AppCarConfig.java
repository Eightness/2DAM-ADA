package com.albert.CRUDCar.utils;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppCarConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components())
                .info(new Info().title("Car CRUD API")
                        .description("Demo API REST in Spring Boot")
                        .contact(new Contact()
                                .name("Albert Lozano")
                                .email("albertlb08@gmail.com")
                                .url("https://www.linkedin.com/in/albertlb08/"))
                        .version("1.0"));
    }
}
