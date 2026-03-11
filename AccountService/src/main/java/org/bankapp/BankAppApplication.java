package org.bankapp;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.bankapp.DTO.AccountDtDTO;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableFeignClients
@EnableConfigurationProperties(AccountDtDTO.class)
@EnableJpaAuditing(auditorAwareRef = "Auditing")
@OpenAPIDefinition(
        info = @Info(
                title = "Selva finance service",
                description = "this app developed by small finance service",
                version = "/v1",
                contact =@Contact(name="selva", url = "https://www.selvafinser.com", email = "selvafinsrv@gmail.com"),
                license = @License(name = "Apache 2.0", url = "https://www.selvafinser.com")
        )

)

public class BankAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(BankAppApplication.class, args);
    }

}
