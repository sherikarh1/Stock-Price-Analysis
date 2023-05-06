package com.edu.msu.stockanalysis.controllers;

import com.edu.msu.stockanalysis.api.StockPriceService;
import com.edu.msu.stockanalysis.payload.response.PriceResponse;
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
@RequestMapping("/price")
@Slf4j
public class StockPriceController {

    private final StockPriceService priceService;

    public StockPriceController(StockPriceService priceService) {
        this.priceService = priceService;
    }

    @GetMapping("/")
    public ResponseEntity<PriceResponse> fetchPrice () throws JsonProcessingException {
        log.info("Received Price Request for /");

        PriceResponse priceResponse = new PriceResponse();
        priceResponse.setSuccess(false);

        log.info("Returning Result {}", priceResponse);

        return ResponseEntity.ok(priceResponse);
    }

    @GetMapping("/{ticker}")
    public ResponseEntity<PriceResponse> fetchPrice (@PathVariable String ticker) throws JsonProcessingException {
        log.info("Received Price Request for ticker {}", ticker);

        PriceResponse priceResponse = priceService.companyDailyPrice(ticker);

        log.info("Returning Result {}", priceResponse);

        return ResponseEntity.ok(priceResponse);
    }
}
