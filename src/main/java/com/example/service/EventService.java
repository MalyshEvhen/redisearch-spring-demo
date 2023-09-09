package com.example.service;

import com.example.domain.dto.EventPostRequest;
import com.example.domain.models.Event;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;

public interface EventService {
    List<Event> searchByBeginDateBetween(@NotNull LocalDate start, @NotNull LocalDate end);
    Iterable<Event> findByTags(@NotEmpty String[] tags);
    Event save(@NotNull @Valid EventPostRequest event);
    Event findById(@NotNull String id);
    Page<Event> findAll(Pageable pageable);
}
