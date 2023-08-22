package com.example.repositories;

import com.example.domain.models.User;
import com.redis.om.spring.autocomplete.Suggestion;
import com.redis.om.spring.repository.RedisDocumentRepository;
import com.redis.om.spring.repository.query.autocomplete.AutoCompleteOptions;

import java.util.List;

public interface UserRepository extends RedisDocumentRepository<User, String> {
    Iterable<User> search(String text);
    List<Suggestion> autoCompleteFullName(String query, AutoCompleteOptions options);
}