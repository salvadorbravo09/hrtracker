package com.hrtracker.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.annotations.tags.Tag;

@OpenAPIDefinition(
        info = @Info(
                title = "HR Tracker API",
                description = "API for employee and department management",
                version = "1.0.0",
                contact = @Contact(
                        name = "Salvador Bravo",
                        url = "https://salvadorbravo.netlify.app/",
                        email = "sa.bravo0901@gmail.com"
                ),
                license = @License(
                        name = "MIT",
                        url = "https://opensource.org/licenses/MIT"
                )
        ),
        servers = {
                @Server(
                        description = "DEV SERVER",
                        url = "http://localhost:8080"
                ),
                @Server(
                        description = "PRODUCTION SERVER",
                        url = "http://hrtracker.com:8080"
                )
        }
)
public class SwaggerConfig {
}
