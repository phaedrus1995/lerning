package com.phaedrus.demo;

import org.springframework.stereotype.Service;

@Service
public class HalloWorldService {
    public String sayHallo(String name) {
        return String.format("Hello %s!", name);
    }
}
