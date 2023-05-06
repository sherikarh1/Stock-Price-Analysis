package com.edu.msu.stockanalysis.model.rest;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Article {

    private Source source;
    private String author;
    private String title;
    private String description;
    private String url;
    private String urlToImage;
    private String publishedAt;
    private String content;

    @Data
    @ToString
    class Source {
        String id;
        String name;
    }

    public String getSourceName () {
        if(source != null)
            return this.source.getName();
        return null;
    }

}


