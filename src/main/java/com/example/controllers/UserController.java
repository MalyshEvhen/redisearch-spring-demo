package com.example.controllers;

import com.example.domain.dto.UserRegistration;
import com.example.domain.models.User;
import com.example.service.UserService;
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
import org.springframework.web.bind.annotation.*;

@Tag(name = "Users", description = "REST controller for managing users.")
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @Operation(summary = "Find all users")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully retrieve page of users",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = User.class)))})
    @GetMapping("/all")
    Page<User> getAll(@RequestParam("size") int size, @RequestParam("page") int page) {
        var pageable = Pageable.ofSize(size).withPage(page);
        return userService.findAll(pageable);
    }

    @Operation(summary = "Get a specific author by ID")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully retrieved the author",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = User.class))}),
            @ApiResponse(
                    responseCode = "400",
                    description = "Id is invalid"),
            @ApiResponse(
                    responseCode = "404",
                    description = "Author not found")})
    @GetMapping("/get-by-id/{id}")
    User byId(@PathVariable("id") String id) {
        return userService.findById(id);
    }

    @Operation(summary = "Save new user")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Successfully save the user",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = User.class))),
            @ApiResponse(
                    responseCode = "400",
                    description = "Registration form is invalid")})
    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    User save(@RequestBody UserRegistration user) {
        return userService.save(user);
    }

    @Operation(summary = "Delete existing user")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    description = "Successfully delete the user"),
            @ApiResponse(
                    responseCode = "400",
                    description = "ID is invalid"),
            @ApiResponse(
                    responseCode = "404",
                    description = "User not found")})
    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void delete(@PathVariable("id") String id) {
        userService.deleteById(id);
    }
}
