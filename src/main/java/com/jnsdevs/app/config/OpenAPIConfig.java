package com.jnsdevs.app.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Autor Jairo Nascimento
 * @Created 30/07/2023 - 14:40
 */
@Configuration
public class OpenAPIConfig {

    @Bean
    OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Hello Swagger OpenAPI")
                        .version("v1")
                        .description("Some description about your API")
                        .termsOfService("http://pub.erudio.com.br/meus-cursos")
                        .license(new License()
                                .name("Apache 2.0")
                                .url("http://pub.erudio.com.br/meus-cursos")));
    }
}
