package com.example.domain.dto;

import com.example.util.CustomMultipartFile;
import jakarta.validation.constraints.Size;

import static com.example.constraints.SharedConstraints.MAX_CONTENT_LENGTH;
import static com.example.constraints.SharedConstraints.MIN_CONTENT_LENGTH;

public record ContentBlockDto(
        Integer order,
        String type,
        Integer columns,
        String imageLabel,
        String imageLink,
        @Size(min = MIN_CONTENT_LENGTH, max = MAX_CONTENT_LENGTH)
        String content
) {
}