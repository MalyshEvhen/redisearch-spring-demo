package com.example.domain.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.Set;

import static com.example.constraints.SharedConstraints.*;

public record EventPostRequest(

        @NotNull
        @NotBlank
        @Size(min = MIN_TITLE_LENGTH, max = MAX_TITLE_LENGTH)
        String title,

        @NotNull
        @NotBlank
        @Size(min = MIN_CONTENT_LENGTH, max = MAX_CONTENT_LENGTH)
        String content,

        Set<String> tags,

        Set<String> artistIds,

        @NotNull
        @Future
        LocalDate begin,

        @NotNull
        @Future
        LocalDate end
) {
}
