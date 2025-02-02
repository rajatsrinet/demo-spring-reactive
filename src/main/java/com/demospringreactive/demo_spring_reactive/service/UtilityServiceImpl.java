package com.demospringreactive.demo_spring_reactive.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class UtilityServiceImpl implements UtilityService {
    @Override
    public String printMessage(String message) {
        Map<Long, String> mapValue = new HashMap<>();
        mapValue.put(1L, "one");
        //Map.of(1L, "One", 2L, "Two", 3L, "Three");
        System.out.println("Message is: " + mapValue.get("1"));
        return mapValue.get("1");
    }

}
