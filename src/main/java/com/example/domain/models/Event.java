package com.example.domain.models;

import com.redis.om.spring.annotations.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Reference;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@RequiredArgsConstructor(staticName = "of")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Document
public class Event {

    @Id
    @Indexed
    @AutoCompletePayload("title")
    private String id;

    @NonNull
    @AutoComplete
    private String title;

    @NonNull
    private String content;

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

    public void addUser(User user) {
        this.artists.add(user);
    }

    public void addTag(String tag) {
        this.tags.add(tag);
    }
}
