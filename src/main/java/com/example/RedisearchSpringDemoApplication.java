package com.example;

import com.redis.om.spring.annotations.EnableRedisDocumentRepositories;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableRedisDocumentRepositories
public class RedisearchSpringDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(RedisearchSpringDemoApplication.class, args);
    }
}
