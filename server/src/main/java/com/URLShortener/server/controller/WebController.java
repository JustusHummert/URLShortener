package com.URLShortener.server.controller;

import com.URLShortener.server.entities.UrlEntity;
import com.URLShortener.server.repositories.UrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.net.URI;
import java.util.Optional;

@Controller
public class WebController {
    @Autowired
    UrlRepository urlRepository;

    //redirects user to saved page
    @GetMapping("{segment}")
    public ResponseEntity<Void> redirect(@PathVariable String segment){
        Optional<UrlEntity> optionalUrlEntity = urlRepository.findById(segment);
        return optionalUrlEntity.<ResponseEntity<Void>>map(urlEntity -> ResponseEntity.status(HttpStatus.FOUND).
                location(URI.create(urlEntity.getUrl())).build()).orElseGet(() ->
                ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping("/URLShortener")
    public String urlShortener(){
        return "urlShortener";
    }
}
