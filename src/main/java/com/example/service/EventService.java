package com.example.service;

import com.example.domain.dto.EventPostRequest;
import com.example.domain.models.Event;

import java.time.LocalDateTime;
import java.util.List;

public interface EventService {
    List<Event> searchByBeginDateBetween(LocalDateTime start, LocalDateTime end);
    Iterable<Event> search(String text);
    Iterable<Event> findByTags(String[] tags);
    Event save(EventPostRequest event);
}
