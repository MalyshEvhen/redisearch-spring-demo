package com.example.controllers;

import com.example.service.SearchService;
import com.redis.om.spring.autocomplete.Suggestion;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/search")
@RequiredArgsConstructor
public class SearchController {

    private final SearchService searchService;

    @GetMapping("/{q}")
    List<Suggestion> search(@PathVariable("q") String query) {
        return searchService.search(query);
    }

}
