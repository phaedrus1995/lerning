package com.phaedrus.demo;

import org.springframework.stereotype.Repository;

@Repository
public class HalloWorldRepository {
    String sayHallo(String name) {
        return String.format("Hallo %s!", name);
    }
}
