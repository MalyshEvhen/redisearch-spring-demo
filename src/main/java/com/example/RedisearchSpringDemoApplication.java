package com.example;

import com.example.domain.models.User;
import com.example.repositories.ArticleRepository;
import com.example.repositories.EventRepository;
import com.example.repositories.UserRepository;
import com.example.util.FakeDataGenerator;
import com.redis.om.spring.annotations.EnableRedisDocumentRepositories;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.HashSet;
import java.util.stream.IntStream;

import static com.example.domain.models.User.Role.ARTIST;
import static com.example.domain.models.User.Role.AUTHOR;
import static com.example.util.FakeDataGenerator.createEvent;
import static com.example.util.FakeDataGenerator.createUser;

@OpenAPIDefinition(
        info = @Info(
                title = "Museum Demo Application",
                version = "0.0.1",
                description = "Application create for demonstration purposes.",
                license = @License(
                        name = "Apache 2.0",
                        url = "http://example.com/licence"),
                contact = @Contact(
                        url = "http://example.com/contacts",
                        name = "Evhen Malysh",
                        email = "email@example.com")))
@SpringBootApplication
@EnableRedisDocumentRepositories
public class RedisearchSpringDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(RedisearchSpringDemoApplication.class, args);
    }

    @Bean
    CommandLineRunner loadTestData(
            UserRepository userRepository,
            ArticleRepository articleRepository,
            EventRepository eventRepository
    ) {
        return args -> {
            IntStream.range(0, 100).forEach(n -> {
                var author = userRepository.save(createUser(AUTHOR));
                IntStream.range(0, 10).forEach(i -> {
                    var article = FakeDataGenerator.createArticle(author);
                    articleRepository.save(article);
                });
            });

            IntStream.range(0, 10).forEach(n -> {
                var artists = new HashSet<User>();
                IntStream.range(0, 10).forEach(i -> {
                    var artist = userRepository.save(createUser(ARTIST));
                    artists.add(artist);
                });
                var event = createEvent(artists);
                eventRepository.save(event);
            });
        };
    }
}
