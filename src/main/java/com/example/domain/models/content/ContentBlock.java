package com.example.domain.models.content;


import com.redis.om.spring.annotations.Document;
import com.redis.om.spring.annotations.Indexed;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.data.annotation.Id;

import static com.example.constraints.SharedConstraints.MAX_CONTENT_LENGTH;
import static com.example.constraints.SharedConstraints.MIN_CONTENT_LENGTH;

@Data
@NoArgsConstructor
@RequiredArgsConstructor(staticName = "of")
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Document
public class ContentBlock {

    @Id
    String id;

    @NonNull
    Integer order;

    @NonNull
    String type;

    @NonNull
    Integer columns;

    @Nullable
    String imageLabel;

    @Nullable
    String imageLink;

    @Nullable
    @Size(min = MIN_CONTENT_LENGTH, max = MAX_CONTENT_LENGTH)
    @Indexed
    String content;

    public ContentBlock(ContentType type) {
        this.type = type.getValue();
    }

    @Getter
    public enum ContentType {
        TEXT("text"),
        IMAGE("image");

        private final String value;

        ContentType(String value) {
            this.value = value;
        }
    }
}