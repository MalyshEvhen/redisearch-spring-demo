package com.example.service.impl;

import com.example.domain.models.Artist;
import com.example.repositories.ArtistRepository;
import com.example.service.ArtistService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ArtistServiceImpl implements ArtistService {
    private final ArtistRepository artistRepository;

    @Override
    public Page<Artist> findAll(Pageable pageable) {
        return artistRepository.findAll(pageable);
    }

    @Override
    public Iterable<Artist> search(String query) {
        return artistRepository.search(query);
    }

    @Override
    public Artist save(Artist author) {
        return artistRepository.save(author);
    }

    @Override
    public void deleteById(String id) {
        artistRepository.deleteById(id);
    }
}
