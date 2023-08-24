package com.example.service;

import com.example.domain.dto.UserRegistration;
import com.example.domain.models.User;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {
    Page<User> findAll(@NotNull Pageable pageable);
    Iterable<User> search(@NotNull @NotBlank String query);
    User save(@NotNull @Valid UserRegistration user);
    void deleteById(@NotNull @NotBlank String id);
    User findById(@NotNull @NotBlank String id);
}
