package com.example.repositories;

import com.example.domain.models.Artist;
import com.redis.om.spring.repository.RedisDocumentRepository;

public interface ArtistRepository extends RedisDocumentRepository<Artist, String> {
    Iterable<Artist> search(String text);
}
