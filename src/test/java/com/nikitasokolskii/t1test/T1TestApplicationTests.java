package com.nikitasokolskii.t1test;

import com.nikitasokolskii.t1test.service.MainService;
import jakarta.servlet.ServletContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class T1TestApplicationTests {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private MainService mainService;

    private String input;

    private Map<Character, Integer> expected;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
        input = "aaaaabcccc";
        expected = new HashMap<>();
        expected.put('a', 5);
        expected.put('c', 4);
        expected.put('b', 1);
    }

    @Test
    public void shouldCountCharacters() {
        assertThat(mainService.countCharacters(input), is(expected));
    }

    @Test
    public void firstCharacterShouldBeA() {
        assertEquals('a', mainService.countCharacters(input)
                .keySet().stream().findFirst().orElseThrow().charValue());
    }

    @Test
    public void lastCharacterShouldBeB() {
        assertEquals('b', mainService.countCharacters(input)
                .keySet().stream().reduce((first, second) -> second).orElseThrow());
    }

    @Test
    public void givenWac_whenServletContext_thenItProvidesMainController() {
        ServletContext servletContext = webApplicationContext.getServletContext();

        assertNotNull(servletContext);
        assertTrue(servletContext instanceof MockServletContext);
        assertNotNull(webApplicationContext.getBean("mainController"));
    }

    @Test
    public void responseShouldThrow400_whenRequestBodyIsNull() throws Exception {
        this.mockMvc.perform(post("/api/v1/countCharacters")).andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void responseShouldThrow405_whenWrongRequestMethod() throws Exception {
        this.mockMvc.perform(get("/api/v1/countCharacters")).andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void responseShouldBe200() throws Exception {
        this.mockMvc.perform(post("/api/v1/countCharacters")
                        .contentType("application/json")
                        .content("{\"input\": \"" + input + "\"}"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }
}
