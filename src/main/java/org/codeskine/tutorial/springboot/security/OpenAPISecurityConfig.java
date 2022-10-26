package org.codeskine.tutorial.springboot.security;


import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPISecurityConfig {

  public static final String AUTHENTICATION_TYPE = "OAuth";

  @Bean
  public OpenAPI api() {
    return new OpenAPI()
        .info(new Info().title("SpringBoot Tutorial API")
            .description("A Spring Boot tutorial with Rest API And Authentication")
            .version("1.0.0"))
        .addSecurityItem(new SecurityRequirement().addList(AUTHENTICATION_TYPE))
        .components(
            new Components()
                .addSecuritySchemes(AUTHENTICATION_TYPE,
                    new SecurityScheme()
                        .name(AUTHENTICATION_TYPE)
                        .type(SecurityScheme.Type.OPENIDCONNECT)
                        .scheme("bearer")
                )
        );
  }
}