package com.joao.pivatto.apirest.doc;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.media.MediaType;
import io.swagger.v3.oas.models.media.Content;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.responses.ApiResponses;
import io.swagger.v3.oas.models.parameters.RequestBody;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API REST Pivatto")
                        .description("API REST Pivatto")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("João Guilherme Pivatto")
                                .url("https://github.com/joaopivatto")
                                .email("jgpivatto@gmail.com")
                        )
                )
                .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
                .components(new Components()
                        .addSecuritySchemes("bearerAuth",
                                new SecurityScheme()
                                        .name("Authorization")
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")
                        )
                );
    }

    @Bean
    public OpenApiCustomizer customiseContentTypes() {
        return openApi -> {
            openApi.getPaths().values().forEach(pathItem -> {
                pathItem.readOperations().forEach(operation -> {
                    if (operation.getRequestBody() == null) {
                        operation.setRequestBody(getJsonRequestBody());
                    }
                    operation.setResponses(getJsonResponse());
                });
            });
        };
    }

    private RequestBody getJsonRequestBody() {
        Content content = new Content().addMediaType("application/json", new MediaType());
        return new RequestBody().content(content);
    }

    private ApiResponses getJsonResponse() {
        Content content = new Content().addMediaType("application/json", new MediaType());
        ApiResponse apiResponse = new ApiResponse().description("Success").content(content);
        ApiResponses responses = new ApiResponses();
        responses.addApiResponse("200", apiResponse);
        return responses;
    }
}
