package com.edu.msu.stockanalysis.api;

import com.edu.msu.stockanalysis.model.rest.CompanyDetails;
import com.edu.msu.stockanalysis.payload.response.CompanyDetailsResponse;
import com.edu.msu.stockanalysis.util.RestServiceUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class StockDetailService {
    @Value("${token}")
    private String token;
    public CompanyDetailsResponse companyDetails(String ticker) {
        String serviceURL = "https://api.tiingo.com/tiingo/daily/" + ticker + "?token="+token;
        CompanyDetails companyDetails = null;
        try {
            String jsonResponse = RestServiceUtil.restGETCall(serviceURL);
            log.info("Response received on REST Call {}", jsonResponse);

            ObjectMapper mapper = new ObjectMapper();
            companyDetails = mapper.readValue(jsonResponse, CompanyDetails.class);


        } catch (Exception e) {
            log.error("Error While making REST call for Detail Result.", e);
        }

        CompanyDetailsResponse response = new CompanyDetailsResponse();
        response.setSuccess(true);
        response.getResults().add(companyDetails);


        return response;
    }
}
