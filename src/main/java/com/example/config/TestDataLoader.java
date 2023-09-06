package com.example.config;

import com.example.domain.models.User;
import com.example.repositories.ArticleRepository;
import com.example.repositories.EventRepository;
import com.example.repositories.UserRepository;
import com.example.util.FakeDataGenerator;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.stream.IntStream;

import static com.example.domain.models.User.Role.ARTIST;
import static com.example.domain.models.User.Role.AUTHOR;
import static com.example.util.FakeDataGenerator.createEvent;
import static com.example.util.FakeDataGenerator.createUser;

@Component
public class TestDataLoader {

    @Bean
    CommandLineRunner loadTestData(
            UserRepository userRepository,
            ArticleRepository articleRepository,
            EventRepository eventRepository
    ) {
        return args -> {
            IntStream.range(0, 30).forEach(n -> {
                var author = userRepository.save(createUser(AUTHOR));
                IntStream.range(0, 10).forEach(i -> {
                    var article = FakeDataGenerator.createArticle(author);
                    articleRepository.save(article);
                });
            });

            IntStream.range(0, 20).forEach(n -> {
                var artists = new HashSet<User>();
                IntStream.range(0, 4).forEach(i -> {
                    var artist = userRepository.save(createUser(ARTIST));
                    artists.add(artist);
                });
                var event = createEvent(artists);
                eventRepository.save(event);
            });
        };
    }

}
