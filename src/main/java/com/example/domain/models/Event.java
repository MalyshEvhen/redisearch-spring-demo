package com.example.domain.models;

import com.redis.om.spring.annotations.Document;
import com.redis.om.spring.annotations.Indexed;
import com.redis.om.spring.annotations.Searchable;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
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
    private String id;

    @NonNull
    @Searchable
    private String title;

    @NonNull
    private String content;

    @Indexed
    private Set<String> tags = new HashSet<>();

    Set<User> artists = new HashSet<>();

    @NonNull
    @Indexed
    private LocalDateTime beginDate;

    @NonNull
    @Indexed
    private LocalDateTime endDate;

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
