package com.edu.msu.stockanalysis.controllers;

import com.edu.msu.stockanalysis.api.StockChartService;
import com.edu.msu.stockanalysis.payload.response.DailyChartResponse;
import com.edu.msu.stockanalysis.payload.response.HistChartResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Calendar;

@Controller
@CrossOrigin(origins = "http://localhost:4200/")
@RequestMapping("/chart")
@Slf4j
public class StockChartController {

    private final StockChartService stockChartService;

    public StockChartController(StockChartService stockChartService) {
        this.stockChartService = stockChartService;
    }

    @GetMapping("/daily/{ticker}/{startDate}")
    public ResponseEntity<DailyChartResponse> getDailyChart (@PathVariable String ticker, @PathVariable String startDate) {
        log.info("Received DailyChart Request with Ticker {} and StartDate {}.", ticker, startDate);

        DailyChartResponse dailyChartDetail = stockChartService.getDailyChartDetail(ticker, startDate);

        log.info("Returning Result {}", dailyChartDetail);

        return ResponseEntity.ok(dailyChartDetail);
    }

    @GetMapping("/historical/{ticker}")
    public ResponseEntity<HistChartResponse> getHistoricalChart (@PathVariable String ticker) {
        log.info("Received HistoricalChart Request with Ticker {}.", ticker);

        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.YEAR, -2);

        String startDate = DateFormatUtils.format(instance.getTime(), "yyyy-MM-dd");

        HistChartResponse histChartResponse = stockChartService.getHistChartDetail(ticker, startDate);

        log.info("Returning Result Count {}", histChartResponse.getTotal());

        return ResponseEntity.ok(histChartResponse);
    }

}
