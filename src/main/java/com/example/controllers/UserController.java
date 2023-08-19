package com.example.controllers;

import com.example.domain.dto.UserRegistration;
import com.example.domain.models.User;
import com.example.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/all")
    Page<User> getAll(@RequestParam("size") int size, @RequestParam("page") int page) {
        var pageable = Pageable.ofSize(size).withPage(page);
        return userService.findAll(pageable);
    }

    @GetMapping("/search/{q}")
    Iterable<User> search(@PathVariable("q") String query) {
        return userService.search(query);
    }

    @PostMapping("/save")
    User save(@RequestBody UserRegistration user) {
        return userService.save(user);
    }

    @DeleteMapping("/delete/{id}")
    void delete(@PathVariable("id") String id) {
        userService.deleteById(id);
    }
}
