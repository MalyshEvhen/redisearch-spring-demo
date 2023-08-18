package com.example.repositories;

import com.example.domain.models.Article;
import com.redis.om.spring.repository.RedisDocumentRepository;

public interface ArticleRepository extends RedisDocumentRepository<Article, String> {
    Iterable<Article> findByAuthorId(String id);
}