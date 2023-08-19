package com.example.service;

import com.example.domain.dto.ArticlePostRequest;
import com.example.domain.models.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ArticleService {
    Page<Article> findAll(Pageable pageable);

    Page<String> getIds(Pageable pageable);

    Iterable<Article> findByAuthorId(String id);

    Iterable<Article> search(String query);

    Article save(ArticlePostRequest article);

    void deleteById(String id);
}
