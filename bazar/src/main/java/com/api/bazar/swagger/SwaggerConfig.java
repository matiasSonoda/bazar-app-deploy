package com.api.bazar.swagger;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
    info= @Info(
        title = "Bazar API Rest",
        description = "Esta API Rest se encarga de facilitar la gestion de clientes, productos y "
                + "se encarga de realizar transacciones de ventas",
        version = "1.0.0",
        contact = @Contact(
            name = "Matias Sonoda",
            url = "https://www.linkedin.com/in/matias-sonoda-840797198/",
            email = "matiasnsonoda@gmail.com")
        ),
        servers = {
            @Server(
                description = "Server de desarrollo",
                url = "http://localhost:8080")
        }
)
public class SwaggerConfig {
}
