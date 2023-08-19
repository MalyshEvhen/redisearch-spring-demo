package com.example.controllers;

import com.example.domain.dto.EventPostRequest;
import com.example.domain.models.Event;
import com.example.service.EventService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/events")
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;

    @Operation(summary = "Find event in between begin and end dates")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully retrieved the collection of events",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Event.class)))})
    @GetMapping("/between")
    List<Event> byNumberOfEmployees(@RequestParam("start")
                                    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
                                    @RequestParam("end")
                                    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end) {
        return eventService.searchByBeginDateBetween(start, end);
    }

    @Operation(summary = "Full text search by part of event title")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully retrieved the collection of events",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Event.class)))})
    @GetMapping("/search/{q}")
    Iterable<Event> search(@PathVariable("q") String q) {
        return eventService.search(q);
    }

    @Operation(summary = "Find events by array of tags")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully retrieved the collection of events",
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
                            schema = @Schema(implementation = Event.class)))})
    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    Event save(@RequestParam("event") EventPostRequest event) {
        return eventService.save(event);
    }
}