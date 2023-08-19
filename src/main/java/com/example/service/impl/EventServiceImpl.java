package com.example.service.impl;

import com.example.domain.dto.EventPostRequest;
import com.example.domain.models.Event;
import com.example.domain.models.Event$;
import com.example.domain.models.User;
import com.example.domain.models.User$;
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
    private final EventRepository eventRepository;
    private final EntityStream entityStream;

    @Override
    public List<Event> searchByBeginDateBetween(LocalDateTime start, LocalDateTime end) {
        return entityStream.of(Event.class)
                .filter(Event$.BEGIN_DATE.between(start, end))
                .collect(Collectors.toList());
    }

    @Override
    public Iterable<Event> search(String text) {
        return eventRepository.search(text);
    }

    @Override
    public List<Event> findByTags(String... tags) {
        return entityStream.of(Event.class)
                .filter(Event$.TAGS.in(tags))
                .collect(Collectors.toList());
    }

    @Override
    public Event save(EventPostRequest event) {
        String[] artistIds = event.artistIds().toArray(String[]::new);
        var artists = entityStream.of(User.class)
                .filter(User$.ID.in(artistIds))
                .collect(Collectors.toSet());
        var eventToSave = Event.of(
                event.title(),
                event.content(),
                event.begin(),
                event.end()
        );
        artists.forEach(eventToSave::addUser);

        return eventRepository.save(eventToSave);
    }
}
