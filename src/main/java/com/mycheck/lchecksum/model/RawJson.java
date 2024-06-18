package com.mycheck.lchecksum.model;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


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

    public String toJsonString() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(this);
    }
}