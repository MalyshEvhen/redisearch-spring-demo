package com.example.controllers;

import com.example.domain.dto.ArticlePostRequest;
import com.example.domain.models.Article;
import com.example.service.ArticleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/articles")
@RequiredArgsConstructor
class ArticleController {
    private final ArticleService articleService;

    @Operation(summary = "Get all articles")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully retrieved the list of articles",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Article.class)))})
    @GetMapping("/all")
    Page<Article> all(@RequestParam("size") int size, @RequestParam("page") int page) {
        Pageable pageable = Pageable.ofSize(size).withPage(page);
        return articleService.findAll(pageable);
    }

    @Operation(summary = "Get a list of all articles by author ID")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully retrieved the list of articles",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Article.class)))})
    @GetMapping("/by-author-id")
    Iterable<Article> byAuthor(@RequestParam("authorId") String id) {
        return articleService.findByAuthorId(id);
    }

    @Operation(summary = "Get a list of all articles by part of article title")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully retrieved the collection of articles",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Article.class)))})
    @GetMapping("/search/{q}")
    Iterable<Article> search(@PathVariable("q") String query) {
        return articleService.search(query);
    }


    @Operation(summary = "Create a new article")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Article created successfully",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Article.class)))})
    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    Article save(@RequestParam("article") ArticlePostRequest article) {
        return articleService.save(article);
    }

    @Operation(summary = "Delete an article by ID")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    description = "Article deleted successfully"),
            @ApiResponse(
                    responseCode = "400",
                    description = "Id is not valid"),
            @ApiResponse(
                    responseCode = "404",
                    description = "Article not found")
    })
    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void delete(@PathVariable("id") String id) {
        articleService.deleteById(id);
    }
}