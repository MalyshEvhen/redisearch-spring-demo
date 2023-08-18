package com.example.domain.models;

import com.redis.om.spring.annotations.Document;
import com.redis.om.spring.annotations.Indexed;
import com.redis.om.spring.annotations.Searchable;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Reference;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@RequiredArgsConstructor(staticName = "of")
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Document
public class Article {

    @Id
    private String id;

    @NonNull
    @Searchable
    private String title;

    @NonNull
    @Searchable
    private String content;

    @Indexed
    private Set<String> tags = new HashSet<>();

    @NonNull
    @Reference
    private Author author;

    @CreatedDate
    private Date createdDate;

    @LastModifiedDate
    private Date lastModifiedDate;
}