package com.edu.msu.stockanalysis.model;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class News {
    private String url;
    private String title;
    private String description;
    private String source;
    private String urlToImage;
    private String publishedAt;

}
