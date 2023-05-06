package com.edu.msu.stockanalysis.controllers;

import com.edu.msu.stockanalysis.api.SearchService;
import com.edu.msu.stockanalysis.payload.response.SearchResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@CrossOrigin(origins = "http://localhost:4200/")
@RequestMapping("/search")
@Slf4j
public class StockSearchController {

    private final SearchService searchService;

    public StockSearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @GetMapping("/")
    public ResponseEntity<SearchResponse> searchTicker () throws JsonProcessingException {
        log.info("Received Search Request for /");
        SearchResponse searchResponse = new SearchResponse();
        searchResponse.setTotal(0);
        log.info("Returning Result {}", searchResponse);
        return ResponseEntity.ok(searchResponse);
    }

    @GetMapping("/{ticker}")
    public ResponseEntity<SearchResponse> searchTicker (@PathVariable String ticker) throws JsonProcessingException {
        log.info("Received Search Request for ticker {}", ticker);

        SearchResponse searchResponse = searchService.searchTickers(ticker);

        log.info("Returning Result {}", searchResponse);
        return ResponseEntity.ok(searchResponse);
    }


}
