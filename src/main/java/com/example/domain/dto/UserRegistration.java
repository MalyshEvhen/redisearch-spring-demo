package com.example.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import static com.example.constraints.SharedConstraints.MAX_CONTENT_LENGTH;
import static com.example.constraints.UserConstraints.EMAIL_REGEXP;
import static com.example.constraints.UserConstraints.MAX_NAME_LENGTH;
import static com.example.constraints.UserConstraints.MIN_NAME_LENGTH;
import static com.example.constraints.SharedConstraints.MAX_FIELD_LENGTH;

public record UserRegistration(

        @NotNull
        @NotBlank
        @Size(min = MIN_NAME_LENGTH, max = MAX_NAME_LENGTH)
        String firstname,

        @NotNull
        @NotBlank
        @Size(min = MIN_NAME_LENGTH, max = MAX_NAME_LENGTH)
        String lastname,

        @NotNull
        @NotBlank
        @Pattern(regexp = EMAIL_REGEXP)
        @Size(max = MAX_FIELD_LENGTH)
        String email,

        @Size(max = MAX_CONTENT_LENGTH)
        String bio
) {
}
