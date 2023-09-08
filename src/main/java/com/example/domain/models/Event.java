package com.example.domain.models;

import com.example.domain.models.content.ContentBlock;
import com.redis.om.spring.annotations.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Reference;

import java.time.LocalDate;
import java.util.*;

@Data
@NoArgsConstructor
@RequiredArgsConstructor(staticName = "of")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Document
public class Event implements Post {

    @Id
    @Indexed
    @AutoCompletePayload(fields = "title")
    private String id;

    @NonNull
    @Searchable
    @AutoComplete
    private String title;

    @NonNull
    @AutoCompletePayload(fields = "title")
    private String description;

    @NonNull
    @Indexed
    private List<ContentBlock> content;

    @Indexed
    private Set<String> tags = new HashSet<>();

    @Reference
    Set<User> artists = new HashSet<>();

    @NonNull
    @Indexed
    private LocalDate beginDate;

    @NonNull
    @Indexed
    private LocalDate endDate;

    @CreatedDate
    private Date createdDate;

    @LastModifiedDate
    private Date lastModifiedDate;

    public void addArtist(User user) {
        this.artists.add(user);
    }

    public void addArtists(Collection<User> users) {
        this.artists.addAll(users);
    }

    public void addTag(String tag) {
        this.tags.add(tag);
    }

}
