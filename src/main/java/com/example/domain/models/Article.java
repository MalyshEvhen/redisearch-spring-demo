package com.example.domain.models;

import com.example.domain.models.User;
import com.example.domain.models.content.ContentBlock;
import com.redis.om.spring.annotations.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@RequiredArgsConstructor(staticName = "of")
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Document
public class Article {

    @Id
    @Indexed
    @AutoCompletePayload("title")
    private String id;

    @NonNull
    @AutoComplete
    private String title;

    @NonNull
    private List<ContentBlock> content;

    @Indexed
    private Set<String> tags = new HashSet<>();

    @NonNull
    @Indexed
    private User author;

    @CreatedDate
    private Date createdDate;

    @LastModifiedDate
    private Date lastModifiedDate;

    public void addTag(String tag) {
        this.tags.add(tag);
    }
}
