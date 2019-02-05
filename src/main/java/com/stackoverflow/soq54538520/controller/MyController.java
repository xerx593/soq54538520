package com.stackoverflow.soq54538520.controller;

import com.stackoverflow.soq54538520.service.ServiceA;
import com.stackoverflow.soq54538520.service.ServiceB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

    @Autowired
    private ServiceA a;

    @Autowired
    private ServiceB b;

    @RequestMapping("/")
    public String index() {
        return String.format("A response: %s, B response: %s", a.greeting(), b.greeting());
    }
}
