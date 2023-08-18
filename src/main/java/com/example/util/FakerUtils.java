package com.example.util;

import com.github.javafaker.Faker;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

public class FakerUtils {

    static Map<ArticleType, String> titles = Map.of(
            ArticleType.INFLUENCE, "The influence on %s of %s",
            ArticleType.LEARN, "What I learn about %s from %s",
            ArticleType.COOK, "How to cook %s with %s",
            ArticleType.TEACH, "Teach your %s how to pilot %s");

    public static String getFakeArticleTitle(Faker faker) {
        var keys = titles.keySet();
        var rando = (long) (keys.size() * Math.random());
        var key = keys.stream().skip(rando).findAny().get();
        var param1 = "";
        var param2 = switch (key) {
            case INFLUENCE -> {
                param1 = faker.ancient().god();
                yield faker.food().dish();
            }
            case LEARN -> {
                param1 = faker.artist().name();
                yield faker.backToTheFuture().character();
            }
            case COOK -> {
                param1 = faker.food().dish();
                yield StringUtils.capitalize(faker.animal().name());
            }
            case TEACH -> {
                param1 = faker.animal().name();
                yield faker.aviation().aircraft();
            }
        };
        var formatted = titles.get(key);
        return String.format(formatted, param1, param2);
    }

    enum ArticleType {
        INFLUENCE, LEARN, COOK, TEACH
    }
}
