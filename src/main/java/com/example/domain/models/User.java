package com.example.domain.models;

import com.redis.om.spring.annotations.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@Document
public class User {

    @Id
    @Indexed
    @AutoCompletePayload("fullName")
    private String id;

    @NonNull
    @Searchable
    private String firstName;

    @NonNull
    @Searchable
    private String lastName;

    @AutoComplete
    private String fullName;

    @NonNull
    @TagIndexed
    private String email;

    @NonNull
    @TextIndexed
    private String bio;

    @Indexed
    private Set<String> roles = new HashSet<>();

    @CreatedDate
    private Date createdDate;

    @LastModifiedDate
    private Date lastModifiedDate;

    public User(String id) {
        this.id = id;
    }

    protected User(
            String id,
            @NonNull String firstName,
            @NonNull String lastName,
            @NonNull String email,
            @NonNull String bio,
            Set<String> roles,
            Date createdDate,
            Date lastModifiedDate
    ) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.fullName = lastName + " " + firstName;
        this.email = email;
        this.bio = bio;
        this.roles = roles;
        this.createdDate = createdDate;
        this.lastModifiedDate = lastModifiedDate;
    }

    private User(
            @NonNull String firstName,
            @NonNull String lastName,
            @NonNull String email,
            @NonNull String bio
    ) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.fullName = lastName + " " + firstName;
        this.email = email;
        this.bio = bio;
    }

    public static User of(
            @NonNull String firstName,
            @NonNull String lastName,
            @NonNull String email,
            @NonNull String bio
    ) {
        return new User(firstName, lastName, email, bio);
    }

    public void addRole(Role role) {
        this.roles.add(role.getValue());
    }

    @Getter
    @RequiredArgsConstructor(access = AccessLevel.PRIVATE)
    public enum Role {
        ARTIST("artist"),
        AUTHOR("author");

        private final String value;
    }
}