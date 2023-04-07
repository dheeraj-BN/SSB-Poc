package com.SecureSeat.Booking.config;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class SwaggerConfig {
 
	@Bean
	public OpenAPI customOpenAPI() {
	    return new OpenAPI()
	            .components(new Components()
	                    .addSecuritySchemes("bearer-key",
	                            new SecurityScheme()
	                                    .type(SecurityScheme.Type.HTTP)
	                                    .scheme("bearer")
	                                    .bearerFormat("JWT")
	                                    .name("Authorization")
	                                    .in(SecurityScheme.In.HEADER)))
	            .info(new Info().title("My API").version("1.0.0"))
	            .addSecurityItem(new SecurityRequirement().addList("bearer-key"))
	            .security(Collections.singletonList(new SecurityRequirement().addList("bearer-key")));
	}

}


