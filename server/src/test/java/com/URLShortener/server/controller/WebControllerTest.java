package com.URLShortener.server.controller;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.URLShortener.server.entities.UrlEntity;
import com.URLShortener.server.repositories.UrlRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest
@AutoConfigureMockMvc
class WebControllerTest {
    @Autowired
    private UrlRepository urlRepository;
    @Autowired
    MockMvc mvc;

    @Test
    void redirect() throws Exception{
        UrlEntity url = new UrlEntity("testUrl", "https://www.example.com/");
        urlRepository.save(url);
        mvc.perform(MockMvcRequestBuilders.get("/testUrl")
                .accept(MediaType.ALL))
                .andExpect(status().isFound());
        urlRepository.delete(url);
        mvc.perform(MockMvcRequestBuilders.get("/testUrl")
                        .accept(MediaType.ALL))
                .andExpect(status().isNotFound());
    }
}