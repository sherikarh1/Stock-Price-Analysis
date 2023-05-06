package com.edu.msu.stockanalysis.controllers;

import com.edu.msu.stockanalysis.api.StockNewsService;
import com.edu.msu.stockanalysis.payload.response.NewsResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@CrossOrigin(origins = "http://localhost:4200/")
@RequestMapping("/news")
@Slf4j
public class NewsController {


    private final StockNewsService stockNewsService;

    public NewsController(StockNewsService stockNewsService) {
        this.stockNewsService = stockNewsService;
    }

    @GetMapping("/{ticker}")
    public ResponseEntity<NewsResponse> getHistoricalChart (@PathVariable String ticker) {
        log.info("Received Ticker News for Ticker {}.", ticker);

        NewsResponse newsResponse = stockNewsService.getNewsAboutTicker(ticker);

        log.info("Returning Result {}", newsResponse.getResults().size());

        return ResponseEntity.ok(newsResponse);
    }
}
