package com.example.service;

import com.example.domain.dto.UserRegistration;
import com.example.domain.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {
    Page<User> findAll(Pageable pageable);
    Iterable<User> search(String query);
    User save(UserRegistration user);
    void deleteById(String id);
    User findById(String id);
}
