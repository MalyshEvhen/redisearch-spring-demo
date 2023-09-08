package com.example.domain.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;
import java.util.Set;

import static com.example.constraints.SharedConstraints.MAX_TITLE_LENGTH;
import static com.example.constraints.SharedConstraints.MIN_TITLE_LENGTH;

public record ArticlePostRequest(

        @NotNull
        @NotBlank
        @Size(min = MIN_TITLE_LENGTH, max = MAX_TITLE_LENGTH)
        String title,

        String description,

        @NotNull
        @Valid
        List<ContentBlockDto> content,

        Set<String> tags,

        @NotNull
        String authorId
) {
}
