package com.example.mapper;

import com.example.domain.dto.ContentBlockDto;
import com.example.domain.dto.EventPostRequest;
import com.example.domain.models.Event;
import com.example.domain.models.content.ContentBlock;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = {ContentMapper.class})
public interface EventMapper {

    @Mapping(target = "endDate", source = "end")
    @Mapping(target = "beginDate", source = "begin")
    @Mapping(target = "content", source = "content")
    @Mapping(target = "artists", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "lastModifiedDate", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    Event toEvent(EventPostRequest request);

}
