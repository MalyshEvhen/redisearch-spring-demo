package com.example.service.impl;

import com.example.domain.dto.EventPostRequest;
import com.example.domain.models.Event;
import com.example.domain.models.Event$;
import com.example.domain.models.User;
import com.example.domain.models.User$;
import com.example.mapper.EventMapper;
import com.example.repositories.EventRepository;
import com.example.service.EventService;
import com.redis.om.spring.search.stream.EntityStream;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final EntityStream entityStream;
    private final EventMapper eventMapper;

    @Override
    public List<Event> searchByBeginDateBetween(LocalDate start, LocalDate end) {
        return entityStream.of(Event.class)
                .filter(Event$.BEGIN_DATE.between(start, end))
                .collect(Collectors.toList());
    }

    @Override
    public List<Event> findByTags(String... tags) {
        return entityStream.of(Event.class)
                .filter(Event$.TAGS.in(tags))
                .collect(Collectors.toList());
    }

    @Override
    public Event save(EventPostRequest request) {
        String[] artistIds = request.artistIds().toArray(String[]::new);
        var artists = entityStream.of(User.class)
                .filter(User$.ID.in(artistIds))
                .collect(Collectors.toSet());
        var eventToSave = eventMapper.toEvent(request);
        eventToSave.addArtists(artists);

        return eventRepository.save(eventToSave);
    }

    @Override
    public Event findById(String id) {
        return eventRepository.findById(id)
                .orElseThrow();
    }

    @Override
    public Page<Event> findAll(Pageable pageable) {
        return eventRepository.findAll(pageable);
    }
}
