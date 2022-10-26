package com.phaedrus.demo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class HalloWorldRepositoryTest {
    @InjectMocks
    private HalloWorldRepository halloWorldRepository;

    @Test
    void should_return_greet() {
        String result = halloWorldRepository.sayHallo("Anja");
        assertEquals("Hallo Anja!", result);
    }

}