package com.example.mapper;

import com.example.domain.dto.ContentBlockDto;
import com.example.domain.models.content.ContentBlock;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ContentMapper {

    @Mapping(target = "id", ignore = true)
    ContentBlock toContent(ContentBlockDto dto);
}
