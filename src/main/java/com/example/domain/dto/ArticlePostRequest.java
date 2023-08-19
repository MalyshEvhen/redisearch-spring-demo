package com.example.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Set;

import static com.example.constraints.SharedConstraints.MAX_CONTENT_LENGTH;
import static com.example.constraints.SharedConstraints.MAX_TITLE_LENGTH;
import static com.example.constraints.SharedConstraints.MIN_CONTENT_LENGTH;
import static com.example.constraints.SharedConstraints.MIN_TITLE_LENGTH;

public record ArticlePostRequest(

        @NotNull
        @NotBlank
        @Size(min = MIN_TITLE_LENGTH, max = MAX_TITLE_LENGTH)
        String title,

        @NotNull
        @NotBlank
        @Size(min = MIN_CONTENT_LENGTH, max = MAX_CONTENT_LENGTH)
        String content,

        Set<String> tags,

        @NotNull
        String authorId
) {
}
