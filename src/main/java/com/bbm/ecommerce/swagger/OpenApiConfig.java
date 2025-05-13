package com.bbm.ecommerce.swagger;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;

@OpenAPIDefinition(
        info = @Info(
                title = "BePrepared",
                description = "A multi-store app",
                version = "1.0",
                contact = @Contact(
                        name = "Belmiro Mungoi",
                        email = "belmiromungoi@gmail.com",
                        url = "https://github.com/BelmiroMungoi"
                ),
                license = @License(
                        name = "Apache License 2.0",
                        url = "https://www.apache.org/licenses/LICENSE-2.0"
                )
        )
)
public class OpenApiConfig {
}
