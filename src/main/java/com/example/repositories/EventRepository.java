package com.example.repositories;

import com.example.domain.models.Event;
import com.redis.om.spring.autocomplete.Suggestion;
import com.redis.om.spring.repository.RedisDocumentRepository;
import com.redis.om.spring.repository.query.autocomplete.AutoCompleteOptions;

import java.util.List;

public interface EventRepository extends RedisDocumentRepository<Event, String> {
    Iterable<Event> search(String text1);
    List<Suggestion> autoCompleteTitle(String query, AutoCompleteOptions options);
}
