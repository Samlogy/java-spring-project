package com.example.api.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;



@OpenAPIDefinition(
        info = @Info(
                title = "Ecommerce API",
                contact = @Contact(
                        name = "Samlogy",
                        url = "https://github.com/Samlogy"
                ),
                version = "1"
        ),
        servers = {
                @Server(
                        url = "http://localhost:8080",
                        description = "Serveur API Env Local"
                ),
                @Server(
                        url = "http://localhost:8180",
                        description = "Serveur IAM Env Local"
                ),
                @Server(
                        url = "http://localhost:4200",
                        description = "Serveur Client Env Local"
                )
        }
)
public class OpenAPIConfig {
}