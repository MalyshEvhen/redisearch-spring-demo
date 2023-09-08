package com.example.domain.dto;

import com.redis.om.spring.annotations.Searchable;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import static com.example.constraints.SharedConstraints.*;

public record EventPostRequest(

        @NotNull
        @NotBlank
        @Size(min = MIN_TITLE_LENGTH, max = MAX_TITLE_LENGTH)
        String title,

        String description,

        List<ContentBlockDto> content,

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
