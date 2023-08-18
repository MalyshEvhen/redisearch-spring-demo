package com.example.service.impl;

import com.example.domain.models.Article;
import com.example.repositories.ArticleRepository;
import com.example.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {
    private final ArticleRepository articleRepository;

    @Override
    public Page<Article> findAll(Pageable pageable) {
        return articleRepository.findAll(pageable);
    }

    @Override
    public Page<String> getIds(Pageable pageable) {
        return articleRepository.getIds(pageable);
    }

    @Override
    public Iterable<Article> findByAuthorId(String id) {
        return articleRepository.findByAuthor_Id(id);
    }

    @Override
    public Iterable<Article> search(String query) {
        return articleRepository.search(query);
    }

    @Override
    public Article save(Article article) {
        return articleRepository.save(article);
    }

    @Override
    public void deleteById(String id) {
        articleRepository.deleteById(id);
    }
}
