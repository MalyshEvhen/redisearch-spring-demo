package com.example.util;

import com.example.domain.models.Article;
import com.example.domain.models.Event;
import com.example.domain.models.User;
import org.junit.jupiter.api.Test;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class FakeDataGeneratorTest {

    @Test
    void createUser() {
        User user = FakeDataGenerator.createUser(User.Role.ARTIST);
        System.out.println(user);

        assertTrue(user instanceof User);
    }

    @Test
    void createArticle() {
        User user = FakeDataGenerator.createUser(User.Role.ARTIST);
        Article article = FakeDataGenerator.createArticle(user);
        System.out.println(article);

        assertTrue(article instanceof Article);
    }

    @Test
    void createEvent() {
        var users = new HashSet<User>();
        User user = FakeDataGenerator.createUser(User.Role.ARTIST);
        users.add(user);
        Event event = FakeDataGenerator.createEvent(users);
        System.out.println(event);

        assertTrue(event instanceof Event);
    }
}