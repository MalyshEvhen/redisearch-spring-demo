package com.example.service.impl;

import com.example.domain.dto.SearchResponse;
import com.example.domain.models.Article;
import com.example.domain.models.Event;
import com.example.domain.models.Post;
import com.example.service.SearchService;
import com.redis.om.spring.search.stream.EntityStream;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SearchServiceImpl implements SearchService {

    private final EntityStream entityStream;

    @Override
    public List<SearchResponse> search(String query) {
        var responses = new LinkedList<SearchResponse>();

        final CharSequence sequence = query.toLowerCase();

        var events = filterBySequence(entityStream.of(Event.class)
                .collect(Collectors.toList()), sequence);

        var articles = filterBySequence(entityStream.of(Article.class)
                .collect(Collectors.toList()), sequence);

        responses.addAll(events);
        responses.addAll(articles);

        return responses;
    }

    private List<SearchResponse> filterBySequence(List<Post> posts, final CharSequence sequence) {
        return posts.parallelStream()
                .filter(post -> matchSequence(post.getTitle(), sequence)
                        || matchSequence(post.getDescription(), sequence))
                .map(this::toSearchResponse)
                .collect(Collectors.toList());
    }

    private boolean matchSequence(String string, CharSequence sequence) {
        return string.toLowerCase().contains(sequence);
    }

    private SearchResponse toSearchResponse(Post post) {
        var type = post instanceof Event ? "event" : "article";
        var response = new SearchResponse(post.getId(), type, post.getTitle());
        response.addPayload("description", post.getDescription());
        return response;
    }

}
