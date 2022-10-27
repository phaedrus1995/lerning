package com.phaedrus.demo.service;

import com.phaedrus.demo.entity.Locker;
import com.phaedrus.demo.repository.HalloWorldRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class HalloWorldServiceTest {

    @Mock
    private HalloWorldRepository halloWorldRepository;

    @InjectMocks
    HalloWorldService halloWorldService;

    @Test
    void should_return_greet() {
        String result = halloWorldService.sayHallo("Benjamin");

        assertEquals("Hallo Benjamin!", result);
    }

    @Test
    void should_query_slots() {
        List<Locker> mockRepoResponse = new ArrayList<>();
        mockRepoResponse.add(new Locker(1, false, "001"));
        mockRepoResponse.add(new Locker(2, false, "002"));
        mockRepoResponse.add(new Locker(3, false, "003"));

        when(halloWorldRepository.findAllByStatus(any())).thenReturn(mockRepoResponse);
        Integer result = halloWorldService.query();

        assertEquals(3, result);
    }
}