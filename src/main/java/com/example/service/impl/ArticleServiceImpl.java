package com.example.service.impl;

import com.example.domain.dto.ArticlePostRequest;
import com.example.domain.models.Article;
import com.example.domain.models.User;
import com.example.domain.models.User$;
import com.example.repositories.ArticleRepository;
import com.example.service.ArticleService;
import com.redis.om.spring.search.stream.EntityStream;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {
    private final ArticleRepository articleRepository;
    private final EntityStream entityStream;

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
    public Article save(ArticlePostRequest article) {
        var author = entityStream.of(User.class)
                .filter(User$.ID.eq(article.authorId()))
                .findAny()
                .orElseThrow();
        var articleToSave = Article.of(article.title(), article.content(), author);

        return articleRepository.save(articleToSave);
    }

    @Override
    public void deleteById(String id) {
        articleRepository.deleteById(id);
    }
}
