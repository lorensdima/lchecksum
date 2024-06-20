package com.mycheck.lchecksum.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mycheck.lchecksum.exceptions.InvalidChecksumValue;
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

    @PostMapping("/validate")
    public HashMap<String, String> postMethodName(@RequestBody RawJson obj) {
        HashMap<String, String> resultMap = new HashMap<>();
        try {
            HashMap<String, Object> jsonMap = new HashMap<>();
            jsonMap = obj.toHashMap();

            String checksum = jsonMap.get("checksum").toString();
            if (checksum.length() != 3) {
                throw new InvalidChecksumValue("Checksum length is not equal to 3");
            }
            String lcheckSumVal = conversionService.computeCheckSum(jsonMap);


            if (checksum.equals(lcheckSumVal)) {
                resultMap.put("result", "true");
                resultMap.put("description", "No data has been lost");
            } else {
                resultMap.put("result", "false");
                resultMap.put("description", "Checksum does not match. Highly likely that some data have been lost");
            }
            resultMap.put("error", "false");
            
        } catch (Exception e) {
            resultMap.put("result", "false");
            resultMap.put("error", "true");
            resultMap.put("description", "Error ocurred: " + e.getMessage());
        }
        
        return resultMap;
    }
    
}
