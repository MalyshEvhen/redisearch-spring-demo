package com.example.util;

import com.example.domain.models.Article;
import com.example.domain.models.Event;
import com.example.domain.models.User;
import com.example.domain.models.content.ContentBlock;
import com.github.javafaker.Faker;
import lombok.Getter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

import static com.example.domain.models.content.ContentBlock.ContentType.IMAGE;
import static com.example.domain.models.content.ContentBlock.ContentType.TEXT;

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
        var content = getContent();
        var description = getRandomText();
        var article = Article.of(title, description, content, user);

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
        var description = getRandomText();
        var content = getContent();
        var begin = getFutureDate(LocalDate.now());
        var end = getFutureDate(begin);
        var event = Event.of(title, description, content, begin, end);

        int counter = ThreadLocalRandom.current().nextInt(5);
        while (counter >= 0) {
            var tag = getTag();
            event.addTag(tag);
            counter--;
        }
        artists.forEach(event::addArtist);
        return event;
    }

    private static List<ContentBlock> getContent() {
        var contentBlocks = new ArrayList<ContentBlock>();

        int counter = ThreadLocalRandom.current().nextInt(4);
        while (counter >= 0) {
            contentBlocks.add(getContentBlock(counter + 1));
            counter--;
        }
        return contentBlocks;
    }

    private static ContentBlock getContentBlock(int order) {
        int column = ThreadLocalRandom.current().nextInt(2);

        var contentBlock = new ContentBlock();
        contentBlock.setContent(getRandomText());
        contentBlock.setOrder(order);
        contentBlock.setColumns(column + 1);
        contentBlock.setType(order % 2 == 0 ? TEXT.getValue() : IMAGE.getValue());
        contentBlock.setImageLabel("image-label");
        contentBlock.setImageLink("image-link");
        return contentBlock;
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
        ARTICLE_1("Архітектор Кавалерідзе: Мистецтво і Геній"),
        ARTICLE_2("Життя та Творчість Архітектора Кавалерідзе: Від Початку до Величі"),
        ARTICLE_3("Кавалерідзе та Архітектурна Революція: Інноваційні Проекти"),
        ARTICLE_4("Архітектурні Шедеври Кавалерідзе: Історія та Аналіз"),
        ARTICLE_5("Технології та Інженерія у Творчості Кавалерідзе"),
        ARTICLE_6("Архітектурна Поезія: Розкриття Таємниць Робіт Кавалерідзе"),
        ARTICLE_7("Майстерність Моделювання: Архітектурні Макети Кавалерідзе"),
        ARTICLE_8("Трансформація Архітектурного Простору: Внесок Кавалерідзе"),
        ARTICLE_9("Спільність та Архітектура: Вплив Кавалерідзе на Місцеве Життя"),
        ARTICLE_10("Архітектурні Візії: Проекти Майбутнього Кавалерідзе"),
        ARTICLE_11("Кавалерідзе та Публічне Мистецтво: Взаємодія з Громадськістю"),
        ARTICLE_12("Архітектурна Магія: Таємниці Творчості Кавалерідзе"),
        ARTICLE_13("Архітектура та Природа: Віддзеркалення у Проектах Кавалерідзе"),
        ARTICLE_14("Кавалерідзе і Сучасні Тенденції в Архітектурі"),
        ARTICLE_15("Скульптура та Декор в Архітектурі: Роль Кавалерідзе"),
        ARTICLE_16("Кавалерідзе і Інтер'єр: Внутрішнє Оформлення Архітектурних Перлин"),
        ARTICLE_17("Архітектурна Інтеракція: Взаємодія із Відвідувачами Музею"),
        ARTICLE_18("Футуристичні Проекти Кавалерідзе: Ідеї для Зміни Міст"),
        ARTICLE_19("Кавалерідзе і Світло: Ігри з Натуральним та Штучним Світлом"),
        ARTICLE_20("Кавалерідзе і Збереження Архітектурної Спадщини"),
        ARTICLE_21("Архітектура та Технології: Інновації в Будівництві"),
        ARTICLE_22("Подорож у Світ Кавалерідзе: Віртуальна Екскурсія по Його Роботах"),
        ARTICLE_23("Архітектурні Творіння Кавалерідзе у Ваших Руках: Макети для Збирання"),
        ARTICLE_24("Мистецтво Гармонії: Архітектурні Пейзажі Кавалерідзе"),
        ARTICLE_25("Історія в Архітектурі: Подорож у Часі з Кавалерідзе"),
        ARTICLE_26("Кавалерідзе та Індустріальна Архітектура: Взаємозв'язок із Промисловим Мистецтвом"),
        ARTICLE_27("Архітектурна Дизайнерська Лабораторія Кавалерідзе: Від Ідеї до Втілення"),
        ARTICLE_28("Вишуканий Декор у Роботах Кавалерідзе: Скульптура та Рельєф"),
        ARTICLE_29("Модернізація Міст: Проекти Кавалерідзе для Зручного Життя"),
        ARTICLE_30("Архітектурна Інспірація: Подорож у Світ Проектів Кавалерідзе"),
        ARTICLE_31("Архітектурні Загадки: Деталі Творчості Кавалерідзе"),
        ARTICLE_32("Історичні Будівлі Кавалерідзе: Відкриття Архітектурного Скарбниці"),
        ARTICLE_33("Митці про Мистецтво: Порівняння Кавалерідзе із Іншими Архітекторами"),
        ARTICLE_34("Міське Планування: Взаємодія Кавалерідзе з Архітектурним Середовищем"),
        ARTICLE_35("Кавалерідзе та Модернізація Міста: Архітектурні Проекти для Всіх"),
        ARTICLE_36("Візуальна Симфонія: Фотографія Архітектурних Шедеврів Кавалерідзе"),
        ARTICLE_37("Місто у Проектах Кавалерідзе: Архітектурні Ідеї для Розвитку"),
        ARTICLE_38("Скульптура в Архітектурі: Великі Роботи Кавалерідзе"),
        ARTICLE_39("Архітектурна Історія: Вплив Кавалерідзе на Сучасність"),
        ARTICLE_40("Кавалерідзе і Культурна Спадщина: Історія Робіт та Реставрація"),
        ARTICLE_41("Архітектурна Геніальність: Творчість із Високою Метою"),
        ARTICLE_42("Архітектурна Анатомія: Розгляд Деталей Робіт Кавалерідзе"),
        ARTICLE_43("Від Проекту до Реалізації: Робочий Процес Кавалерідзе"),
        ARTICLE_44("Архітектурні Експерименти: Інноваційні Технології Кавалерідзе"),
        ARTICLE_45("Кавалерідзе із Висоти Пташиного Польоту: Дрон-Зйомка Робіт"),
        ARTICLE_46("Архітектурна Місія Кавалерідзе: Зміна Міст через Дизайн"),
        ARTICLE_47("Архітектурні Вечори: Мистецтво Інтер'єру у Роботах Кавалерідзе"),
        ARTICLE_48("Місто Інновацій: Архітектурні Рішення для Майбутнього"),
        ARTICLE_49("Сакральна Архітектура Кавалерідзе: Проекти Релігійних Об'єктів"),
        ARTICLE_50("Кавалерідзе та Модернізація Міст: Архітектурні Ідеї для Зручного Життя");

        private final String value;

        MuseumArticleTitles(String value) {
            this.value = value;
        }
    }

}
