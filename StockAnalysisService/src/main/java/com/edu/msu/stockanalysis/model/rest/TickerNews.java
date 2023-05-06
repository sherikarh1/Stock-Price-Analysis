package com.edu.msu.stockanalysis.model.rest;

import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class TickerNews {

    private String status;
    private int totalResults;
    private List<Article> articles;
}
