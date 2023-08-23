package com.example.service.impl;

import com.example.repositories.ArticleRepository;
import com.example.repositories.EventRepository;
import com.example.repositories.UserRepository;
import com.example.service.SearchService;
import com.redis.om.spring.autocomplete.Suggestion;
import com.redis.om.spring.repository.query.autocomplete.AutoCompleteOptions;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class SearchServiceImpl implements SearchService {

    private static final String USER_BY_ID_URL = "/api/users/get-by-id/";
    private static final String EVENT_BY_ID_URL = "/api/events/get-by-id/";
    private static final String ARTICLE_BY_ID_URL = "/api/articles/get-by-id/";

    private final UserRepository userRepository;
    private final EventRepository eventRepository;
    private final ArticleRepository articleRepository;

    @Override
    public List<Suggestion> search(String query) {
        var totalSuggestion = new LinkedList<Suggestion>();

        var userSuggestions = getUserSuggestions(query);
        var eventSuggestions = getEventSuggestions(query);
        var articleSuggestion = getArticleSuggestions(query);

        totalSuggestion.addAll(eventSuggestions);
        totalSuggestion.addAll(articleSuggestion);
        totalSuggestion.addAll(userSuggestions);

        return totalSuggestion;
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
        suggestionPayload.put("redirectPath",USER_BY_ID_URL + suggestionPayload.get("id"));

        return new Suggestion(suggestion.getValue(), suggestion.getScore(), suggestionPayload);
    }

    private Suggestion addEventRedirectUri(Suggestion suggestion) {
        Map<String, Object> suggestionPayload = suggestion.getPayload();
        suggestionPayload.put("redirectPath",EVENT_BY_ID_URL + suggestionPayload.get("id"));

        return new Suggestion(suggestion.getValue(), suggestion.getScore(), suggestionPayload);
    }

    private Suggestion addArticleRedirectUri(Suggestion suggestion) {
        Map<String, Object> suggestionPayload = suggestion.getPayload();
        suggestionPayload.put("redirectPath",ARTICLE_BY_ID_URL + suggestionPayload.get("id"));

        return new Suggestion(suggestion.getValue(), suggestion.getScore(), suggestionPayload);
    }

}
