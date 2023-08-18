package com.example.controllers;

import com.example.domain.models.Article;
import com.example.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/articles")
@RequiredArgsConstructor
class ArticleController {
    private final ArticleService articleService;

    @GetMapping("/all")
    Page<Article> all(Pageable pageable) {
        return articleService.findAll(pageable);
    }

    @GetMapping("/all-ids")
    Page<String> allIds(Pageable pageable) {
        return articleService.getIds(pageable);
    }

    @GetMapping("/author/{id}")
    Iterable<Article> byAuthor(@PathVariable("id") String id) {
        return articleService.findByAuthorId(id);
    }

    @GetMapping("/search/{q}")
    Iterable<Article> search(@PathVariable("q") String query) {
        return articleService.search(query);
    }

    @PostMapping("/save")
    Article save(@RequestParam("article") Article article) {
        return articleService.save(article);
    }

    @DeleteMapping("/delete/{id}")
    void delete(@PathVariable("id") String id) {
        articleService.deleteById(id);
    }
}