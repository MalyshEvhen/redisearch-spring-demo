package com.example;

import com.example.config.StorageProperties;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@OpenAPIDefinition(
        info = @Info(
                title = "Museum Demo Application",
                version = "0.0.1",
                description = "Application create for demonstration purposes.",
                license = @License(
                        name = "Apache 2.0",
                        url = "https://example.com/licence"),
                contact = @Contact(
                        url = "https://example.com/contacts",
                        name = "Evhen Malysh",
                        email = "email@example.com")))
@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class RedisearchSpringDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(RedisearchSpringDemoApplication.class, args);
    }

}
