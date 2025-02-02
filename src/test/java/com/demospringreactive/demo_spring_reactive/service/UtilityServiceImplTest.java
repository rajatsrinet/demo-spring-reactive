package com.demospringreactive.demo_spring_reactive.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UtilityServiceImplTest {

    @Autowired
    UtilityServiceImpl utilityService;

    @Test
    public void testPrintMessage() {
        String val = utilityService.printMessage("Test Message");
        assertEquals("One", val);
       // assertEquals("Message is: One\n", outContent.toString());
        
    }
}