package com.example.service;

import java.nio.file.Path;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public interface StorageService {

    String store(MultipartFile file);

    List<String> loadAll();

    Path load(final String filename);

    byte[] loadAsResource(final String filename);

    void deleteAll();

    void init();

}
