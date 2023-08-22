package com.example.service;

import com.redis.om.spring.autocomplete.Suggestion;

import java.util.List;

public interface SearchService {
    List<Suggestion> search(String query);
}
