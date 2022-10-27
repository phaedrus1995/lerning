package com.phaedrus.demo.service;

import com.phaedrus.demo.entity.Locker;
import com.phaedrus.demo.repository.HalloWorldRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HalloWorldService {
    private final HalloWorldRepository halloWorldRepository;

    public String sayHallo(String name) {
        return String.format("Hallo %s!", name);
    }

    public Integer query() {
        List<Locker> emptyLockers = halloWorldRepository.findAllByStatus(false);
        return emptyLockers.size();
    }
}
