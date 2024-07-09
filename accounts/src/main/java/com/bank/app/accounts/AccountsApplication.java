package com.bank.app.accounts;

import com.bank.app.accounts.dto.AccountsContactInfoDto;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@EnableConfigurationProperties(value = {AccountsContactInfoDto.class})
@OpenAPIDefinition(info = @Info(title = "Accounts microservice REST API Documentation",
                                description = "Accounts microservice belongs to Bank app",
                                // version of your app
                                version = "v1",
                                // who to contact regarding the documentation
                                contact = @Contact(name = "Ege D", email = "ege@ege.com",
                                                   url = "https://github.com/egedemirtas"),
                                // to access the licensing info
                                license = @License(name = "Apache 2.0", url = "www.mylicenseinfo.com")),
                   // in case people want to know more about our app and API
                   externalDocs = @ExternalDocumentation(description = "Accounts micro service",
                                                         url = "www.moreinfo" + ".com/swagger-uı.html"))
public class AccountsApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccountsApplication.class, args);
    }

}
