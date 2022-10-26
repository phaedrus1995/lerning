package com.phaedrus.demo;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@WebMvcTest(HalloWorldController.class)
class DemoApplicationTests {

	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext webApplicationContext;

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

}
