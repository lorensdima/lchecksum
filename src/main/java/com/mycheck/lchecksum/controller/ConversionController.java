package com.mycheck.lchecksum.controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mycheck.lchecksum.model.RawJson;

@RestController
public class ConversionController {
    
    @PostMapping("/convert")
    public String receiveExampleObject(@RequestBody RawJson obj) {
        String jsonString = "";
        try {
            jsonString = obj.toJsonString();
            System.out.println("JSON String: " + jsonString);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } 
        return "Received: " + jsonString;
    }
}
