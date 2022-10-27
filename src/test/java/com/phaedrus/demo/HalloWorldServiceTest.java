package com.phaedrus.demo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class HalloWorldServiceTest {

    @Mock
    private HalloWorldRepository halloWorldRepository;

    @InjectMocks
    HalloWorldService halloWorldService;

    @Test
    void should_return_greet() {
        when(halloWorldRepository.sayHallo("Benjamin")).thenReturn("Hallo Benjamin!");

        String result = halloWorldService.sayHallo("Benjamin");

        assertEquals("Hallo Benjamin!", result);
    }

    @Test
    void should_query_slots() {
        when(halloWorldRepository.query()).thenReturn(true);

        Boolean result = halloWorldService.query();

        assertEquals(true, result);
    }
}