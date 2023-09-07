package com.example.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.service.StorageService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.io.IOException;

@RestController
@RequestMapping("/api/image")
@RequiredArgsConstructor
public class ImagesController {
    
    private final StorageService storageService;
    
    @Operation(summary = "Get image by filename")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully retrieved JPEG image",
                    content = @Content(
                            mediaType = "image/jpeg",
                            schema = @Schema(implementation = byte[].class)))})
    @GetMapping(value = "/{filename}", produces = MediaType.IMAGE_JPEG_VALUE)
    byte[] getImage(@PathVariable("filename") String filename) throws IOException {
        Resource resource = storageService.loadAsResource(filename);
        return resource.getContentAsByteArray();
    }

    @Operation(summary = "Save JPEG image")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Successfully saved JPEG image")})
    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    void handleFileUpload(@RequestParam("file") final MultipartFile file) {
        storageService.store(file);
    }
}
