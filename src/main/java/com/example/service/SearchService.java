package com.example.service;

import com.example.domain.dto.SearchResponse;

import java.util.List;

public interface SearchService {
    List<SearchResponse> search(String query);
}
