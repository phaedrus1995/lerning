package com.phaedrus.demo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class HalloWorldServiceTest {
    @Test
    void should_return_greet() {
        HalloWorldService halloWorldService = new HalloWorldService();
        String result = halloWorldService.sayHallo("Benjamin");

        assertEquals("Hallo Benjamin!", result);
    }
}