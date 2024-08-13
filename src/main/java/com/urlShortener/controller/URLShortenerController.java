package com.urlShortener.controller;

import com.urlShortener.entities.UrlEntity;
import com.urlShortener.repositories.UrlRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.reactive.function.client.WebClient;


@Controller
@RequestMapping
public class URLShortenerController {
    @Autowired
    private UrlRepository urlRepository;

    //add new url, segment combination
    @PostMapping(path = "/addUrl")
    public String addUrl(String url, String segment, HttpServletRequest request){
        //check if segment already exists
        if(urlRepository.existsById(segment))
            return "redirect:/?alreadyTaken";
        //check if url is valid
        if(!(url.startsWith("http://")||url.startsWith("https://")))
            url = "https://" +url;
        String response =  WebClient.create(url).get().accept(MediaType.ALL).retrieve().bodyToMono(String.class)
                   .onErrorReturn("error").block();
        //Invalid Url
        if(response == null || response.equals("error"))
            return "redirect:/?invalidUrl";
        urlRepository.save(new UrlEntity(segment, url));
        String requestUrl = request.getRequestURL().toString();
        requestUrl = requestUrl.substring(0, requestUrl.length() - request.getRequestURI().length());
        return "redirect:/?shortUrl=" + requestUrl + "/" + segment;
    }
}
