package com.edu.msu.stockanalysis.controllers;

import com.edu.msu.stockanalysis.api.StockDetailService;
import com.edu.msu.stockanalysis.payload.response.CompanyDetailsResponse;
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
@RequestMapping("/detail")
@Slf4j
public class StockDetailController {

    private final StockDetailService stockDetailService;

    public StockDetailController(StockDetailService stockDetailService) {
        this.stockDetailService = stockDetailService;
    }

    @GetMapping("/")
    public ResponseEntity<CompanyDetailsResponse> searchTicker () throws JsonProcessingException {
        log.info("Received Request in /");

        CompanyDetailsResponse detailsResponse = new CompanyDetailsResponse();
        detailsResponse.setSuccess(false);

        log.info("Returning Result {}", detailsResponse);

        return ResponseEntity.ok(detailsResponse);
    }

    @GetMapping("/{ticker}")
    public ResponseEntity<CompanyDetailsResponse> searchTicker (@PathVariable String ticker) throws JsonProcessingException {
        log.info("Received Request in / {}", ticker);

        CompanyDetailsResponse detailsResponse = stockDetailService.companyDetails(ticker);

        log.info("Returning Result {}", detailsResponse);

        return ResponseEntity.ok(detailsResponse);
    }
}
