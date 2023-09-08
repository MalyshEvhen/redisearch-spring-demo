package com.example.repositories;

import com.example.domain.models.Article;
import com.redis.om.spring.autocomplete.Suggestion;
import com.redis.om.spring.repository.RedisDocumentRepository;
import com.redis.om.spring.repository.query.autocomplete.AutoCompleteOptions;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ArticleRepository extends RedisDocumentRepository<Article, String> {
    Page<Article> findByAuthor_Id(String id, Pageable pageable);
    Iterable<Article> search(String text1);
    List<Suggestion> autoCompleteTitle(String query, AutoCompleteOptions options);
}