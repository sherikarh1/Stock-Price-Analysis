package com.edu.msu.stockanalysis.api;

import com.edu.msu.stockanalysis.model.*;
import com.edu.msu.stockanalysis.payload.response.DailyChartResponse;
import com.edu.msu.stockanalysis.payload.response.HistChartResponse;
import com.edu.msu.stockanalysis.model.rest.Chart;
import com.edu.msu.stockanalysis.model.rest.HistoricalChart;
import com.edu.msu.stockanalysis.util.RestServiceUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
public class StockChartService {

    @Value("${token}")
    private String token;
    public DailyChartResponse getDailyChartDetail (String ticker, String startDate) {
        DailyChartResponse response = new DailyChartResponse();

        String requestURL = "https://api.tiingo.com/iex/"+ticker+"/prices?startDate="+startDate+"&resampleFreq=4min&token="+token;
        try {

            String jsonResponse = RestServiceUtil.restGETCall(requestURL);

            ObjectMapper mapper = new ObjectMapper();
            List<Chart> charts = mapper.readValue(jsonResponse, new TypeReference<List<Chart>>() {});

            List<DailyChart> dailyCharts = charts.stream().map(this::apply).collect(Collectors.toList());

            response.setResults(dailyCharts);
            response.setTotal(dailyCharts.size());

        } catch (Exception e) {
            log.error("Error in processing Daily Chart Detail.", e);
        }

        return response;
    }


    private DailyChart apply (Chart chart) {
        DailyChart dailyChart = new DailyChart();
        dailyChart.setDate(chart.getDate().getTime());
        dailyChart.setClose(((chart.getClose())));
        return dailyChart;
    }


    public HistChartResponse getHistChartDetail (String ticker, String startDate) {
        HistChartResponse response = new HistChartResponse();

        String requestURL = "https://api.tiingo.com/iex/"+ticker+"/prices?startDate="+startDate+"&resampleFreq=4min&token=1dedd603367941c79da0e3d89a6c170817a15d77";
        try {

            String jsonResponse = RestServiceUtil.restGETCall(requestURL);

            ObjectMapper mapper = new ObjectMapper();
            List<HistoricalChart> charts = mapper.readValue(jsonResponse, new TypeReference<List<HistoricalChart>>() {});

            List<HistChart> histChartList = charts.stream().map(this::applyHist).collect(Collectors.toList());

            response.setSuccess(true);
            response.setResults(histChartList);
            response.setTotal(histChartList.size());

        } catch (Exception e) {
            log.error("Error in processing Daily Chart Detail.", e);
        }

        return response;
    }

    private HistChart applyHist (HistoricalChart chart) {
        HistChart histChart = new HistChart();
        histChart.setClose((chart.getClose()));
        histChart.setOpen((chart.getOpen()));
        histChart.setLow((chart.getLow()));
        histChart.setHigh((chart.getHigh()));
        histChart.setVolume((chart.getVolume()));
        histChart.setDate(chart.getDate().getTime());

        return histChart;

    }
}

