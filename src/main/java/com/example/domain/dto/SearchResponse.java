package com.example.domain.dto;

import java.util.HashMap;
import java.util.Map;

public record SearchResponse(
   String id,
   String type,
   String title,
   Map<String, Object> payload

) {
    public SearchResponse(String id, String type, String title){
        this(id, type, title, new HashMap<>());
    }

    public void addPayload(String key, Object value) {
        this.payload.put(key, value);
    }
}
