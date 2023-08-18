package com.example.service;

import com.example.domain.models.Author;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AuthorService {
    Page<Author> findAll(Pageable pageable);

    Iterable<Author> search(String query);

    Author save(Author author);

    void deleteById(String id);
}
