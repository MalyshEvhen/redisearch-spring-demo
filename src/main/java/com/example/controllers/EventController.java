package com.example.controllers;

import com.example.domain.dto.EventPostRequest;
import com.example.domain.models.Article;
import com.example.domain.models.Event;
import com.example.service.EventService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Validated
@Tag(name = "Events", description = "REST controller for managing museum events.")
@RestController
@RequestMapping("/api/events")
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;

    @Operation(summary = "Get all events")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully retrieved the page of events or empty page," +
                            " if no articles is present in DB",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Event.class)))})
    @GetMapping("/all")
    Page<Event> getAll(@RequestParam("size") int size, @RequestParam("page") int page) {
        Pageable pageable = Pageable.ofSize(size).withPage(page);
        return eventService.findAll(pageable);
    }

    @Operation(summary = "Find event in between begin and end dates.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully retrieved the collection of events," +
                            " or empty list if no events was found in this period.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Event.class)))})
    @GetMapping("/between")
    List<Event> byStartAndEndOf(@RequestParam("start")
                                    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDate start,
                                @RequestParam("end")
                                    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDate end) {
        return eventService.searchByBeginDateBetween(start, end);
    }

    @Operation(summary = "Find Event by ID")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Event found successfully",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Event.class))),
            @ApiResponse(
                    responseCode = "400",
                    description = "ID is invalid."),
            @ApiResponse(
                    responseCode = "404",
                    description = "Event not found")})
    @GetMapping("/get-by-id/{id}")
    Event byId(@PathVariable("id") String id) {
        return eventService.findById(id);
    }

    @Operation(summary = "Find events by array of tags")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully retrieved the collection of events," +
                            " or empty list if no events found by given tags.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Event.class)))})
    @GetMapping("/tags")
    Iterable<Event> allByTags(@RequestParam("tags") String... tags) {
        return eventService.findByTags(tags);
    }

    @Operation(summary = "Create new event")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Event successfully created",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Event.class))),
            @ApiResponse(
                    responseCode = "400",
                    description = "Event form is invalid.")})
    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    Event save(@RequestBody EventPostRequest event) {
        return eventService.save(event);
    }


    @GetMapping("/search/{q}")
    Iterable<Event> search(@PathVariable("q") String query) {
        return eventService.search(query);
    }
}