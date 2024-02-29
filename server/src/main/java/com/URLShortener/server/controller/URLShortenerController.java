package com.URLShortener.server.controller;

import com.URLShortener.server.entities.UrlEntity;
import com.URLShortener.server.repositories.UrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path = "/URLShortener")
public class URLShortenerController {
    @Autowired
    private UrlRepository urlRepository;

    //add new url, segment combination
    @PostMapping(path = "/addUrl")
    public @ResponseBody String addUrl(String url, String segment){
        if(urlRepository.existsById(segment))
            return "segment is already taken.";
        urlRepository.save(new UrlEntity(segment, url));
        return "saved";
    }
}
