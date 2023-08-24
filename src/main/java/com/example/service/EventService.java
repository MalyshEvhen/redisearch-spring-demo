package com.example.service;

import com.example.domain.dto.EventPostRequest;
import com.example.domain.models.Event;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;

public interface EventService {
    List<Event> searchByBeginDateBetween(@NotNull LocalDate start, @NotNull LocalDate end);
    Iterable<Event> search(@NotNull @NotBlank String text);
    Iterable<Event> findByTags(@NotEmpty String[] tags);
    Event save(@NotNull @Valid EventPostRequest event);
    Event findById(@NotNull String id);
}
