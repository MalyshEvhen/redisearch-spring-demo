package com.example.util;

import com.example.domain.models.Article;
import com.example.domain.models.Event;
import com.example.domain.models.User;
import com.github.javafaker.Faker;
import lombok.Getter;

import java.time.LocalDate;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

public class FakeDataGenerator {

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
        var title = getArticleTitle();
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
        var title = getEventTitle();
        var content = getRandomText();
        var begin = getFutureDate(LocalDate.now());
        var end = getFutureDate(begin);
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

    private static LocalDate getFutureDate(LocalDate from) {
        long min = from.toEpochDay();
        long max = min + 10L;
        long randomSecond = ThreadLocalRandom.current().nextLong(min, max);
        return LocalDate.ofEpochDay(randomSecond);
    }

    private static String getTag() {
        var tags = Tags.values();
        int i = ThreadLocalRandom.current().nextInt(tags.length);
        return tags[i].getValue();
    }

    private static String getRandomText() {
        Faker faker = new Faker(new Locale("uk"));
        return faker.lorem().paragraph();
    }

    private static String getArticleTitle() {
        var museumArticleTitles = MuseumArticleTitles.values();
        int i = ThreadLocalRandom.current().nextInt(museumArticleTitles.length);
        return museumArticleTitles[i].getValue();
    }

    private static String getEventTitle() {
        var museumEventTitles = MuseumEventTitles.values();
        int i = ThreadLocalRandom.current().nextInt(museumEventTitles.length);
        return museumEventTitles[i].getValue();
    }

    private static String getEmail() {
        Faker faker = new Faker();
        return faker.internet().emailAddress();
    }

    private static String getLastname() {
        Faker faker = new Faker(new Locale("uk"));
        return faker.name().lastName();
    }

    private static String getFirstname() {
        Faker faker = new Faker(new Locale("uk"));
        return faker.name().firstName();
    }

    @Getter
    public enum Tags {
        EXHIBITION("виставка"),
        LECTURE("лекція"),
        WORKSHOP("майстер-клас"),
        CONCERT("концерт"),
        TOUR("екскурсія"),
        ART_INSTALLATION("художня інсталяція"),
        CULTURAL_EVENT("культурна подія"),
        HISTORY("історія"),
        SCIENCE("наука"),
        TECHNOLOGY("технології"),
        ART("мистецтво"),
        HERITAGE("спадщина"),
        ARCHITECTURE("архітектура"),
        FILM_SCREENING("показ фільму"),
        PHOTOGRAPHY("фотографія"),
        LITERATURE("література"),
        PERFORMANCE("вистава"),
        FOOD("їжа"),
        FAMILY_FRIENDLY("для сім'ї");

        private final String value;

        Tags(String value) {
            this.value = value;
        }

    }

    @Getter
    private enum MuseumEventTitles {
        EVENT_1("Виставка архітектурних творінь"),
        EVENT_2("Лекція про життя і творчість Кавалерідзе"),
        EVENT_3("Архітектурна екскурсія"),
        EVENT_4("Майстер-клас із малювання архітектурних макетів"),
        EVENT_5("Показ фільмів про архітектуру"),
        EVENT_6("Панельна дискусія: Архітектура майбутнього"),
        EVENT_7("Фотовиставка: Кавалерідзе і архітектурна спадщина"),
        EVENT_8("Архітектура та історія: Діалог із експертами"),
        EVENT_9("Архітектура і природа: Взаємозв'язок"),
        EVENT_10("Конкурс архітектурного дизайну"),
        EVENT_11("Архітектура та сталість: Нові підходи"),
        EVENT_12("Архітектура в поп-культурі: Виставка та обговорення"),
        EVENT_13("Філософія архітектури: Лекція і дискусія"),
        EVENT_14("Форум з міського планування: Майбутнє міст"),
        EVENT_15("Фотографія архітектурного силуету міста"),
        EVENT_16("Архітектура та світло: Творчість Кавалерідзе"),
        EVENT_17("Виставка архітектурних матеріалів"),
        EVENT_18("Панельна дискусія: Історія архітектури"),
        EVENT_19("Архітектура та спільнота: Вплив на життя містян"),
        EVENT_20("Архітектура і технології: Інновації в будівництві");

        private final String value;

        MuseumEventTitles(String value) {
            this.value = value;
        }

    }

    @Getter
    private enum MuseumArticleTitles {
        ARTICLE_1("Архітектор Кавалерідзе: Портрет Генія"),
        ARTICLE_2("Архітектурна Експресія: Творчість Кавалерідзе"),
        ARTICLE_3("Архітектурні Шедеври Кавалерідзе: Спостереження і Аналіз"),
        ARTICLE_4("Техніка та Інновації: Кавалерідзе та Сучасна Архітектура"),
        ARTICLE_5("Кавалерідзе та Архітектурний Ландшафт: Взаємодія Мистецтва та Природи"),
        ARTICLE_6("Архітектурна Візія: Від Проекту до Реалізації"),
        ARTICLE_7("Архітектура та Культура: Вплив Кавалерідзе на Спільноту"),
        ARTICLE_8("Архітектурний Аналіз: Огляд Робіт Кавалерідзе"),
        ARTICLE_9("Мистецтво Світла: Архітектурна Взаємодія з Інженерією"),
        ARTICLE_10("Кавалерідзе та Архітектурне Спадщина: Збереження та Відновлення"),
        ARTICLE_11("Від Концепції до Макету: Процес Творчості Кавалерідзе"),
        ARTICLE_12("Архітектурні Ретроспективи: Діалог із Експертами"),
        ARTICLE_13("Сучасність у Віддзеркаленні Архітектури: Кавалерідзе та Технології"),
        ARTICLE_14("Кавалерідзе та Модернізація Простору: Сучасні Тенденції"),
        ARTICLE_15("Архітектурна Культовість: Великі Проекти Кавалерідзе"),
        ARTICLE_16("Архітектура та Громадськість: Вплив на Спільні Цінності"),
        ARTICLE_17("Архітектурна Майстерність: Секрети Творчості Кавалерідзе"),
        ARTICLE_18("Світло та Колір у Роботах Кавалерідзе: Архітектурна Поезія"),
        ARTICLE_19("Кавалерідзе та Архітектурний Оновлення Міст: Сучасні Проекти"),
        ARTICLE_20("Архітектурна Інтеракція: Взаємодія із Відвідувачами Музею");

        private final String value;

        MuseumArticleTitles(String value) {
            this.value = value;
        }
    }

}
