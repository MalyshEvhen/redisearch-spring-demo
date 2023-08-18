package com.example.domain.models;

import com.redis.om.spring.annotations.Document;
import com.redis.om.spring.annotations.Indexed;
import com.redis.om.spring.annotations.Searchable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@RequiredArgsConstructor(staticName = "of")
@AllArgsConstructor
@Document
public class Event {
    @Id
    private String id;

    @NonNull
    @Searchable
    private String title;

    @NonNull
    @Searchable
    private String content;

    @Indexed
    private LocalDateTime beginDate;

    @Indexed
    private LocalDateTime endDate;

}
