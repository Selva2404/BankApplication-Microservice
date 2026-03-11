package org.cardservice;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.cardservice.DTO.CardsDtDTO;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "cardAuditing")
@OpenAPIDefinition(
        info = @Info(
                title = "card handling service",
                description = "this service is hand all card reladed operations",
                version = "/v1",
                license = @License(
                        name = "apache 2.0",
                        url = "https://www.selvafinsrv.com"
                ),
                contact=@Contact(
                        name = "selva",
                        email = "selva@gmail.com",
                        url = "https://www.selvafinsrv.com"
                )
        ),
        externalDocs =@ExternalDocumentation(
                url = "https://www.selvafinsrv.com",
                description = "refer this document for more details"
        )

)
@EnableConfigurationProperties(CardsDtDTO.class)
public class CardServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CardServiceApplication.class, args);
    }
}
