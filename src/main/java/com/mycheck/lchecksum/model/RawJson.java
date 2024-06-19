package com.mycheck.lchecksum.model;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.HashMap;

public class RawJson {
    
    @JsonProperty("firstName")
    private String name;

    @JsonProperty("age")
    private int age;

    public RawJson() {
    }

    public String getName() {
        return name;
    }

    public HashMap<String, Object> toHashMap() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(this);
            ObjectMapper objectMapper2 = new ObjectMapper();
            return objectMapper2.readValue(json, new TypeReference<HashMap<String, Object>>() {});
        } catch (IOException e) {
            e.printStackTrace(); 
            return new HashMap<>();
        }
    }
}