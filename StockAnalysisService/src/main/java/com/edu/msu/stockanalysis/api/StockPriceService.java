package com.edu.msu.stockanalysis.api;

import com.edu.msu.stockanalysis.model.CompanyPrice;
import com.edu.msu.stockanalysis.payload.response.PriceResponse;
import com.edu.msu.stockanalysis.model.rest.PriceDetail;
import com.edu.msu.stockanalysis.util.RestServiceUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class StockPriceService {

    @Value("${token}")
    private String token;

    public PriceResponse companyDailyPrice (String ticker) {
        String requestURL = "https://api.tiingo.com/iex/?tickers="+ticker+"&token="+token;
        
        PriceResponse priceResponse = new PriceResponse();
        String jsonResponse;
        try {
            jsonResponse = RestServiceUtil.restGETCall(requestURL);
            ObjectMapper mapper = new ObjectMapper();
            List<PriceDetail> priceDetails = mapper.readValue(jsonResponse, new TypeReference<List<PriceDetail>>() {});
            List<CompanyPrice> companyPrices = priceDetails.stream().map(this::apply).collect(Collectors.toList());

            priceResponse.setResults(companyPrices);
            priceResponse.setSuccess(true);


        } catch (Exception e) {
            throw new RuntimeException(e);
        }



        return priceResponse;
    }

    private CompanyPrice apply (PriceDetail priceDetail) {
        CompanyPrice companyPrice = new CompanyPrice();
        companyPrice.setTicker(priceDetail.getTicker());
        companyPrice.setLast(priceDetail.getLast());
        companyPrice.setAskPrice(priceDetail.getAskPrice());
        companyPrice.setAskSize(priceDetail.getAskSize());
        companyPrice.setBidPrize(priceDetail.getBidPrice());
        companyPrice.setBidSize(priceDetail.getBidSize());
        companyPrice.setPrevClose(priceDetail.getPrevClose());
        companyPrice.setVolume(priceDetail.getVolume());
        companyPrice.setTimestamp(priceDetail.getTimestamp());

        companyPrice.setHigh(priceDetail.getHigh());
        companyPrice.setLow(priceDetail.getLow());
        companyPrice.setOpen(companyPrice.getOpen());
        return companyPrice;
    }
}
