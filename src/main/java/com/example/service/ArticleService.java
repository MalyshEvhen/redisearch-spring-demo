package com.example.service;

import com.example.domain.dto.ArticlePostRequest;
import com.example.domain.models.Article;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ArticleService {
    Page<Article> findAll(@NotNull Pageable pageable);
    Page<String> getIds(@NotNull Pageable pageable);
    Page<Article> findByAuthorId(@NotNull @NotBlank String id, @NotNull Pageable pageable);
    Iterable<Article> search(@NotNull @NotBlank String query);
    Article save(@NotNull @Valid ArticlePostRequest article);
    void deleteById(@NotNull @NotBlank String id);
    Article findById(@NotNull @NotBlank String id);
}
