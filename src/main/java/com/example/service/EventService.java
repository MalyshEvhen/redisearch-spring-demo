package com.example.service;

import com.example.domain.dto.EventPostRequest;
import com.example.domain.models.Event;

import java.time.LocalDate;
import java.util.List;

public interface EventService {
    List<Event> searchByBeginDateBetween(LocalDate start, LocalDate end);
    Iterable<Event> search(String text);
    Iterable<Event> findByTags(String[] tags);
    Event save(EventPostRequest event);
    Iterable<Event> altSearch(String q);
    Event findById(String id);
}
