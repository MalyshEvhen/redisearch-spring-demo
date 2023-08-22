package com.example.service.impl;

import com.example.domain.dto.UserRegistration;
import com.example.domain.models.User;
import com.example.repositories.UserRepository;
import com.example.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public Page<User> findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public Iterable<User> search(String query) {
        return userRepository.search(query);
    }

    @Override
    public User save(UserRegistration user) {
        var userToSave = User.of(
                user.firstname(),
                user.lastname(),
                user.email(),
                user.bio()
        );
        return userRepository.save(userToSave);
    }

    @Override
    public void deleteById(String id) {
        userRepository.deleteById(id);
    }

    @Override
    public User findById(String id) {
        return userRepository.findById(id)
                .orElseThrow();
    }
}
