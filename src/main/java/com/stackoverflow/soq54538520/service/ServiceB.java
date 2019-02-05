package com.stackoverflow.soq54538520.service;

import org.springframework.stereotype.Service;

@Service
public class ServiceB implements Greeting {

    @Override
    public String greeting() {
        return "I am real ServiceB!";
    }
    
}
