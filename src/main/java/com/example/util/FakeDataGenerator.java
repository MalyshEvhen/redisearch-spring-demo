package com.example.util;

import com.example.domain.models.Article;
import com.example.domain.models.User;
import com.example.domain.models.Event;
import com.github.javafaker.Faker;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

public class FakeDataGenerator {
    private static final Faker faker = new Faker();

    public static User createUser(User.Role role) {
        var firstname = getFirstname();
        var lastname = getLastname();
        var email = getEmail();
        var bio = getRandomText();
        User user = User.of(firstname, lastname, email, bio);
        user.addRole(role);

        return user;
    }

    public static Article createArticle(User user) {
        var title = getTitle();
        var content = getRandomText();
        var article = Article.of(title, content, user);

        int counter = ThreadLocalRandom.current().nextInt(5);
        while (counter >= 0) {
            var tag = getTag();
            article.addTag(tag);
            counter--;
        }
        return article;
    }

    public static Event createEvent(Set<User> artists) {
        var title = getTitle();
        var content = getRandomText();
        var begin = getFutureDateTime(LocalDateTime.now());
        var end = getFutureDateTime(begin);
        var event = Event.of(title, content, begin, end);

        int counter = ThreadLocalRandom.current().nextInt(5);
        while (counter >= 0) {
            var tag = getTag();
            event.addTag(tag);
            counter--;
        }
        artists.forEach(event::addUser);
        return event;
    }

    private static LocalDateTime getFutureDateTime(LocalDateTime from) {
        long min = from.toEpochSecond(ZoneOffset.MIN);
        long max = min + 2_000_000L;
        long randomSecond = ThreadLocalRandom.current().nextLong(min, max);
        return LocalDateTime.ofEpochSecond(randomSecond, 0, ZoneOffset.MIN);
    }

    private static String getTag() {
        return faker.book().genre();
    }

    private static String getRandomText() {
        return faker.lorem().paragraph();
    }

    private static String getTitle() {
        return faker.book().title();
    }

    private static String getEmail() {
        return faker.internet().emailAddress();
    }

    private static String getLastname() {
        return faker.address().lastName();
    }

    private static String getFirstname() {
        return faker.name().firstName();
    }
}
