package com.example.controllers;

import com.example.domain.models.Artist;
import com.example.service.ArtistService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/artists")
@RequiredArgsConstructor
public class ArtistController {
    private final ArtistService artistService;

    @GetMapping("/all")
    Page<Artist> getAll(Pageable pageable) {
        return artistService.findAll(pageable);
    }

    @GetMapping("/search/{q}")
    Iterable<Artist> search(@PathVariable("q") String query) {
        return artistService.search(query);
    }

    @PostMapping("/save")
    Artist save(@RequestParam("author") Artist author) {
        return artistService.save(author);
    }

    @DeleteMapping("/delete/{id}")
    void delete(@PathVariable("id") String id) {
        artistService.deleteById(id);
    }
}
