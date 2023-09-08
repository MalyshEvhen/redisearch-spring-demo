package com.example.controllers;

import com.example.domain.dto.SearchRequest;
import com.example.domain.dto.SearchResponse;
import com.example.service.SearchService;
import com.redis.om.spring.autocomplete.Suggestion;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Search", description = "REST controller for global search.")
@RestController
@RequestMapping("/api/search")
@RequiredArgsConstructor
public class SearchController {

    private final SearchService searchService;

    @Operation(summary = "Endpoint for autocompletion search")
    @ApiResponse(
            responseCode = "200",
            description = "List of suggestions was retrieved," +
                    " or empty list ,if it is no suggestions.")
    @GetMapping("/autocomplete/{q}")
    List<Suggestion> autocomplete(@PathVariable("q") String query) {
        return searchService.autocomplete(query);
    }

    @Operation(summary = "Endpoint for text search")
    @ApiResponse(
            responseCode = "200",
            description = "List of search responses was retrieved," +
                    " or empty list ,if it is no suggestions.")
    @GetMapping("/{q}")
    List<SearchResponse> search(@PathVariable("q") String query) {
        return searchService.search(query);
    }

}
