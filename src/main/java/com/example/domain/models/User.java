package com.example.domain.models;

import com.redis.om.spring.annotations.Document;
import com.redis.om.spring.annotations.Indexed;
import com.redis.om.spring.annotations.Searchable;
import com.redis.om.spring.annotations.TagIndexed;
import com.redis.om.spring.annotations.TextIndexed;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@RequiredArgsConstructor(staticName = "of")
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Document
public class User {

    @Id
    @Indexed
    private String id;

    @NonNull
    @Searchable
    private String firstName;

    @NonNull
    @Searchable
    private String lastName;

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