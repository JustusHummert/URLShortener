package com.URLShortener.server.controller;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import com.URLShortener.server.repositories.UrlRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@AutoConfigureMockMvc
class URLShortenerControllerTest {
    @Autowired
    private UrlRepository urlRepository;
    @Autowired
    MockMvc mvc;

    @Test
    void addUrl() throws Exception{
        //valid request
        mvc.perform(MockMvcRequestBuilders.post("/URLShortener/addUrl")
                        .param("url", "www.example.com")
                        .param("segment", "example")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("shortened Url is: http://localhost/example")));
        //segment taken
        mvc.perform(MockMvcRequestBuilders.post("/URLShortener/addUrl")
                        .param("url", "www.example.com")
                        .param("segment", "example")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("segment is already taken.")));
        //noinspection OptionalGetWithoutIsPresent
        assertEquals("https://www.example.com", urlRepository.findById("example").get().getUrl(),
                "Url should be saved with https:// added");
        urlRepository.deleteById("example");
        //invalid url
        mvc.perform(MockMvcRequestBuilders.post("/URLShortener/addUrl")
                        .param("url", "test")
                        .param("segment", "example")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("invalid url")));
    }
}