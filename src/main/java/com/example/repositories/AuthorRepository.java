package com.example.repositories;

import com.example.domain.models.Author;
import com.redis.om.spring.repository.RedisDocumentRepository;

public interface AuthorRepository extends RedisDocumentRepository<Author, String> {
}