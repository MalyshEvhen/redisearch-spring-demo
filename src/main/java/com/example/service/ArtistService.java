package com.example.service;

import com.example.domain.models.Artist;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ArtistService {
    Page<Artist> findAll(Pageable pageable);

    Iterable<Artist> search(String query);

    Artist save(Artist author);

    void deleteById(String id);
}
