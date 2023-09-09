package com.example.repositories;

import com.example.domain.models.Event;
import com.redis.om.spring.repository.RedisDocumentRepository;

public interface EventRepository extends RedisDocumentRepository<Event, String> {
}
