package com.example.service;

import java.nio.file.Path;
import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface StorageService {

    void store(MultipartFile file);

    List<String> loadAll();

    Path load(final String filename);

    Resource loadAsResource(final String filename);

    void deleteAll();

    void init();

}
