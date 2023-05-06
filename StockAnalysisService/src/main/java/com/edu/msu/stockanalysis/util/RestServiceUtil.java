package com.edu.msu.stockanalysis.util;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

public class RestServiceUtil {

    /**
     * Method to make REST Call and return JSON response.
     * @param serviceUrl
     * @return
     * @throws Exception
     */
    public static String restGETCall (String serviceUrl) throws Exception{

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<String>(headers);

        String response = restTemplate.exchange(
                serviceUrl, HttpMethod.GET, entity, String.class).getBody();

        return response;

    }
}
