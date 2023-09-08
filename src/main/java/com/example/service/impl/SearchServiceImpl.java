package com.example.service.impl;

import com.example.domain.dto.SearchResponse;
import com.example.domain.models.*;
import com.example.repositories.ArticleRepository;
import com.example.repositories.EventRepository;
import com.example.repositories.UserRepository;
import com.example.service.SearchService;
import com.redis.om.spring.autocomplete.Suggestion;
import com.redis.om.spring.repository.query.autocomplete.AutoCompleteOptions;
import com.redis.om.spring.search.stream.EntityStream;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SearchServiceImpl implements SearchService {

    private static final String USER_BY_ID_URL = "/api/users/get-by-id/";
    private static final String EVENT_BY_ID_URL = "/api/events/get-by-id/";
    private static final String ARTICLE_BY_ID_URL = "/api/articles/get-by-id/";

    private final UserRepository userRepository;
    private final EventRepository eventRepository;
    private final ArticleRepository articleRepository;
    private final EntityStream entityStream;

    @Override
    public List<Suggestion> autocomplete(String query) {
        var totalSuggestion = new LinkedList<Suggestion>();

        var userSuggestions = getUserSuggestions(query);
        var eventSuggestions = getEventSuggestions(query);
        var articleSuggestion = getArticleSuggestions(query);

        totalSuggestion.addAll(eventSuggestions);
        totalSuggestion.addAll(articleSuggestion);
        totalSuggestion.addAll(userSuggestions);

        return totalSuggestion;
    }

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

    private List<Suggestion> getUserSuggestions(String query) {
        return userRepository.autoCompleteFullName(query, AutoCompleteOptions.get().withPayload().fuzzy())
                .stream()
                .map(this::addUserRedirectUri)
                .toList();
    }

    private List<Suggestion> getEventSuggestions(String query) {
        return eventRepository.autoCompleteTitle(query, AutoCompleteOptions.get().withPayload().fuzzy())
                .stream()
                .map(this::addEventRedirectUri)
                .toList();
    }

    private List<Suggestion> getArticleSuggestions(String query) {
        return articleRepository.autoCompleteTitle(query, AutoCompleteOptions.get().withPayload().fuzzy())
                .stream()
                .map(this::addArticleRedirectUri)
                .toList();
    }

    private Suggestion addUserRedirectUri(Suggestion suggestion) {
        Map<String, Object> suggestionPayload = suggestion.getPayload();
        suggestionPayload.put("redirectPath", USER_BY_ID_URL + suggestionPayload.get("id"));

        return new Suggestion(suggestion.getValue(), suggestion.getScore(), suggestionPayload);
    }

    private Suggestion addEventRedirectUri(Suggestion suggestion) {
        Map<String, Object> suggestionPayload = suggestion.getPayload();
        suggestionPayload.put("redirectPath", EVENT_BY_ID_URL + suggestionPayload.get("id"));

        return new Suggestion(suggestion.getValue(), suggestion.getScore(), suggestionPayload);
    }

    private Suggestion addArticleRedirectUri(Suggestion suggestion) {
        Map<String, Object> suggestionPayload = suggestion.getPayload();
        suggestionPayload.put("redirectPath", ARTICLE_BY_ID_URL + suggestionPayload.get("id"));

        return new Suggestion(suggestion.getValue(), suggestion.getScore(), suggestionPayload);
    }

}
