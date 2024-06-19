package com.mycheck.lchecksum.service;

import java.util.HashMap;
import java.util.Locale;

import org.springframework.stereotype.Service;

@Service
public class ConversionService {
    public String computeCheckSum(HashMap<String, Object> jsonMap) {
        int sum = 0;
        for(String key: jsonMap.keySet()) {
            System.out.println(key);
            for (char c : jsonMap.get(key).toString().toCharArray() ) {
                sum += (int) c;
            }
        }
        return  String.format(Locale.ENGLISH, "%03d", sum % 256);
    }
}
