package com.phaedrus.demo;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HalloWorldService {
    private final HalloWorldRepository halloWorldRepository;

    public String sayHallo(String name) {
        return halloWorldRepository.sayHallo(name);
    }
}
