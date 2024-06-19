package com.mycheck.lchecksum.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mycheck.lchecksum.model.RawJson;
import com.mycheck.lchecksum.service.ConversionService;

@RestController
public class ConversionController {

    @Autowired
    private ConversionService conversionService;
    
    @PostMapping("/convert")
    public HashMap<String, Object> receiveExampleObject(@RequestBody RawJson obj) {
        HashMap<String, Object> jsonMap = new HashMap<>();
        jsonMap = obj.toHashMap();

        String lcheckSumVal = conversionService.computeCheckSum(jsonMap);
        jsonMap.put("checksum", lcheckSumVal);
        
        return jsonMap;
    }
}
