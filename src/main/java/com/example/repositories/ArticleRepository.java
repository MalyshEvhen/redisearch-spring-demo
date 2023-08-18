package com.example.repositories;

import com.example.domain.models.Article;
import com.redis.om.spring.repository.RedisDocumentRepository;

public interface ArticleRepository extends RedisDocumentRepository<Article, String> {
    Iterable<Article> findByAuthor_Id(String id);

    Iterable<Article> search(String text);
}