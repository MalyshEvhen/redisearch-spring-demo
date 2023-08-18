package com.example.controllers;

import com.example.domain.models.Article;
import com.example.repositories.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/articles")
@RequiredArgsConstructor
class ArticleController {
    private final ArticleRepository articleRepository;

    @GetMapping("all")
    Page<Article> all(Pageable pageable) {
        return articleRepository.findAll(pageable);
    }

    @GetMapping("all-ids")
    Page<String> allIds(Pageable pageable) {
        return articleRepository.getIds(pageable);
    }

    @GetMapping("author/{id}")
    Iterable<Article> byAuthor(@PathVariable("id") String id) {
        return articleRepository.findByAuthorId(id);
    }
}