package com.example.service;

import com.example.domain.dto.SearchResponse;
import com.redis.om.spring.autocomplete.Suggestion;

import java.util.List;

public interface SearchService {
    List<Suggestion> autocomplete(String query);
    List<SearchResponse> search(String query);
}
