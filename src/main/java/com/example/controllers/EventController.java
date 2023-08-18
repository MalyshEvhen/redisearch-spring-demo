package com.example.controllers;

import com.example.domain.models.Event;
import com.example.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/events")
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;

    @GetMapping("/between")
    List<Event> byNumberOfEmployees(@RequestParam("start")
                                    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
                                    @RequestParam("end")
                                    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end) {
        return eventService.searchByBeginDateBetween(start, end);
    }

    @GetMapping("/search/{q}")
    Iterable<Event> search(@PathVariable("q") String q) {
        return eventService.search(q);
    }

    @GetMapping("/tags")
    Iterable<Event> allByTags(@RequestParam("tags") String... tags) {
        return eventService.findByTags(tags);
    }

    @PostMapping("/save")
    Event save(@RequestParam("event") Event event) {
        return eventService.save(event);
    }
}