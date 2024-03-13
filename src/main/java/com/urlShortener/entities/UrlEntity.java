package com.urlShortener.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class UrlEntity {

    //The segment the user chooses
    @Id
    private String segment;

    //The url someone opening the segment gets send to
    private String url;

    public UrlEntity(){};

    public UrlEntity(String segment, String url){
        this.segment = segment;
        this.url = url;
    }

    public String getSegment() {
        return segment;
    }

    public void setSegment(String segment) {
        this.segment = segment;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
