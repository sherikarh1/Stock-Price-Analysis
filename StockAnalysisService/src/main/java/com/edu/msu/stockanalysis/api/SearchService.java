package com.edu.msu.stockanalysis.api;

import com.edu.msu.stockanalysis.model.rest.TickerDetail;
import com.edu.msu.stockanalysis.payload.response.SearchResponse;
import com.edu.msu.stockanalysis.model.CompanySearchResult;
import com.edu.msu.stockanalysis.util.RestServiceUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class SearchService {
    @Value("${token}")
    private String token;
    public SearchResponse searchTickers(String ticker) throws JsonProcessingException {

        String serviceURL = "https://api.tiingo.com/tiingo/utilities/search?query=" + ticker + "&token="+token;
        List<TickerDetail> tickers = null;

        String jsonResponse;
        try {

            jsonResponse = RestServiceUtil.restGETCall(serviceURL);

            log.info("Response received on REST Call {}", jsonResponse);

            ObjectMapper mapper = new ObjectMapper();
            tickers = mapper.readValue(jsonResponse, new TypeReference<List<TickerDetail>>() {});

        } catch (Exception e) {
            log.error("Exception Occurred while making REST call for Search Query.",e);
        }
        log.info("{}", tickers);

        List<CompanySearchResult> companies = new ArrayList<>();
        for (TickerDetail tickerDetail : tickers) {
            CompanySearchResult apply = apply(tickerDetail);
            companies.add(apply);
        }

        SearchResponse searchResponse = new SearchResponse();
        searchResponse.setTotal(companies.size());
        searchResponse.setResults(companies);

        return searchResponse;
    }

    private static CompanySearchResult apply(TickerDetail t) {
        CompanySearchResult companySearchResult = new CompanySearchResult();
        companySearchResult.setTicker(t.getTicker());
        companySearchResult.setName(t.getName());
        return companySearchResult;
    }

}
