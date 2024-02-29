package com.URLShortener.server.controller;

import com.URLShortener.server.entities.UrlEntity;
import com.URLShortener.server.repositories.UrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.net.URI;
import java.util.Optional;

@Controller
public class WebController {
    @Autowired
    UrlRepository urlRepository;
    @GetMapping("")
    public ResponseEntity<Void> redirect(String s){
        Optional<UrlEntity> optionalUrlEntity = urlRepository.findById(s);
        return optionalUrlEntity.<ResponseEntity<Void>>map(urlEntity -> ResponseEntity.status(HttpStatus.FOUND).
                location(URI.create(urlEntity.getUrl())).build()).orElseGet(() ->
                ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
}
