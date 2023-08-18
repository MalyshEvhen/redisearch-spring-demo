package com.example.controllers;

import com.example.domain.models.Author;
import com.example.service.AuthorService;
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
@RequestMapping("/authors")
@RequiredArgsConstructor
public class AuthorController {
    private final AuthorService authorService;

    @GetMapping("/all")
    Page<Author> getAll(Pageable pageable) {
        return authorService.findAll(pageable);
    }

    @GetMapping("/search/{q}")
    Iterable<Author> search(@PathVariable("q") String query) {
        return authorService.search(query);
    }

    @PostMapping("/save")
    Author save(@RequestParam("author") Author author) {
        return authorService.save(author);
    }

    @DeleteMapping("/delete/{id}")
    void delete(@PathVariable("id") String id) {
        authorService.deleteById(id);
    }
}
