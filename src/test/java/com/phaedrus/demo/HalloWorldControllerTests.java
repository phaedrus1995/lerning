package com.phaedrus.demo;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@WebMvcTest(HalloWorldController.class)
public class HalloWorldControllerTests {
    private MockMvc mockMvc;

    @MockBean
    private HalloWorldService halloWorldService;

    @BeforeAll
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(new HalloWorldController(halloWorldService)).build();
    }

    @Test
    void should_return_hallo_world() throws Exception {
        when(halloWorldService.sayHallo(any())).thenReturn("hallo world");
        mockMvc.perform(get("/hello")).andExpect(status().isOk())
                .andExpect(content().string("hallo world"));;
    }

    @Test
    void should_return_name_according_to_input() throws Exception {
        when(halloWorldService.sayHallo("Xenia")).thenReturn("hallo Xenia");
        mockMvc.perform(get("/hello?name=Xenia")).andExpect(status().isOk())
                .andExpect(content().string("hallo Xenia"));
    }

    @Test
    void should_get_free_when_slots_are_free() throws Exception {
        when(halloWorldService.query()).thenReturn(true);
        mockMvc.perform(get("/query")).andExpect(status().isOk())
                .andExpect(jsonPath("status").value(true))
                .andExpect(jsonPath("message").value("it is free"));
    }

    @Test
    void should_get_not_free_when_slots_are_occupied() throws Exception {
        when(halloWorldService.query()).thenReturn(false);
        mockMvc.perform(get("/query")).andExpect(status().isOk())
                .andExpect(jsonPath("status").value(false))
                .andExpect(jsonPath("message").value("sorry, no free slot"));
    }
}
