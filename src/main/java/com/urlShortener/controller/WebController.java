package com.urlShortener.controller;

import com.urlShortener.entities.UrlEntity;
import com.urlShortener.repositories.UrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;
import java.util.Optional;

@Controller
public class WebController {
    @Autowired
    UrlRepository urlRepository;

    //redirects user to saved page
    @GetMapping("{segment}")
    public String redirect(@PathVariable String segment){
        Optional<UrlEntity> optionalUrlEntity = urlRepository.findById(segment);
        if(optionalUrlEntity.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Segment not found");
        return "redirect:" + optionalUrlEntity.get().getUrl();
    }

    @GetMapping("")
    public String urlShortener(){
        return "urlShortener";
    }
}
