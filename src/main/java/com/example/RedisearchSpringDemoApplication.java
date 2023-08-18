package com.example;

import com.example.domain.models.Artist;
import com.example.repositories.ArticleRepository;
import com.example.repositories.ArtistRepository;
import com.example.repositories.AuthorRepository;
import com.example.repositories.EventRepository;
import com.example.util.FakeDataGenerator;
import com.redis.om.spring.annotations.EnableRedisDocumentRepositories;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.HashSet;
import java.util.stream.IntStream;

import static com.example.util.FakeDataGenerator.createArtist;
import static com.example.util.FakeDataGenerator.createAuthor;
import static com.example.util.FakeDataGenerator.createEvent;

@SpringBootApplication
@EnableRedisDocumentRepositories
public class RedisearchSpringDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(RedisearchSpringDemoApplication.class, args);
    }

    @Bean
    CommandLineRunner loadTestData(
            AuthorRepository authorRepository,
            ArtistRepository artistRepository,
            ArticleRepository articleRepository,
            EventRepository eventRepository
    ) {
        return args -> {
            IntStream.range(0, 100).forEach(n -> {
                var author = authorRepository.save(createAuthor());
                IntStream.range(0, 10).forEach(i -> {
                    var article = FakeDataGenerator.createArticle(author);
                    articleRepository.save(article);
                });
            });

            IntStream.range(0, 10).forEach(n -> {
                var artists = new HashSet<Artist>();
                IntStream.range(0, 10).forEach(i -> {
                    var artist = artistRepository.save(createArtist());
                    artists.add(artist);
                });
                var event = createEvent(artists);
                eventRepository.save(event);
            });
        };
    }
}
