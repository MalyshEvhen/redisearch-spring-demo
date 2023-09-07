package com.example.controllers;

import com.example.domain.dto.ArticlePostRequest;
import com.example.domain.models.Article;
import com.example.service.ArticleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@Tag(name = "Articles", description = "REST controller for managing museum articles.")
@RestController
@RequestMapping("/api/articles")
@RequiredArgsConstructor
class ArticleController {
    private final ArticleService articleService;

    @Operation(summary = "Get all articles")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully retrieved the page of articles or empty page," +
                            " if no articles is present in DB",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Article.class)))})
    @GetMapping("/all")
    Page<Article> all(@RequestParam("size") int size, @RequestParam("page") int page) {
        Pageable pageable = Pageable.ofSize(size).withPage(page);
        return articleService.findAll(pageable);
    }

    @Operation(summary = "Find article by ID")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully retrieved article",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Article.class))),
            @ApiResponse(
                    responseCode = "400",
                    description = "ID is invalid"),
            @ApiResponse(
                    responseCode = "404",
                    description = "Article not found")})
    @GetMapping("/get-by-id/{id}")
    Article byId(@PathVariable("id") String id) {
        return articleService.findById(id);
    }

    @Operation(summary = "Get a list of all articles by author ID")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully retrieved the list of articles or empty list," +
                            " if no articles been found by author ID",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Article.class)))})
    @GetMapping("/by-author-id")
    Page<Article> byAuthor(@RequestParam("authorId") String id,
                           @RequestParam("size") int size,
                           @RequestParam("page") int page) {
        var pageable = Pageable.ofSize(size).withPage(page);
        return articleService.findByAuthorId(id, pageable);
    }

    @Operation(summary = "Create a new article")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Article created successfully",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Article.class))),
            @ApiResponse(
                    responseCode = "400",
                    description = "Article request is not valid.")})
    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    Article save(@RequestBody ArticlePostRequest article) {
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