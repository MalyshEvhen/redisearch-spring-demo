package com.example.service;

import com.example.domain.models.Event;

import java.time.LocalDateTime;
import java.util.List;

public interface EventService {
    List<Event> searchByBeginDateBetween(LocalDateTime start, LocalDateTime end);
    Iterable<Event> search(String text);
}
