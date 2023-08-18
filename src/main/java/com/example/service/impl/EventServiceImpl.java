package com.example.service.impl;

import com.example.domain.models.Event;
import com.example.domain.Event$;
import com.example.repositories.EventRepository;
import com.example.service.EventService;
import com.redis.om.spring.search.stream.EntityStream;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final EventRepository repository;

    private final EntityStream entityStream;

    @Override
    public List<Event> searchByBeginDateBetween(LocalDateTime start, LocalDateTime end) {

        return entityStream.of(Event.class)
                .filter(Event$.BEGIN_DATE.between(start, end))
                .collect(Collectors.toList());
    }

    @Override
    public Iterable<Event> search(String text) {
        return repository.search(text);
    }
}
