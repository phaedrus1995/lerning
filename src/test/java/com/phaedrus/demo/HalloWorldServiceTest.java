package com.phaedrus.demo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class HalloWorldServiceTest {

    @Autowired
    private HalloWorldRepository halloWorldRepository;

    @InjectMocks
    HalloWorldService halloWorldService;

    @Test
    void should_return_greet() {
        when(halloWorldRepository.sayHallo("Benjamin")).thenReturn("Hallo Benjamin!");

        String result = halloWorldService.sayHallo("Benjamin");

        assertEquals("Hallo Benjamin!", result);
    }
}