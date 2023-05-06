package com.edu.msu.stockanalysis.api;

import com.edu.msu.stockanalysis.model.News;
import com.edu.msu.stockanalysis.model.rest.Article;
import com.edu.msu.stockanalysis.model.rest.TickerNews;
import com.edu.msu.stockanalysis.payload.response.NewsResponse;
import com.edu.msu.stockanalysis.util.RestServiceUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
public class StockNewsService {

    @Value("${token}")
    private String token;

    public NewsResponse getNewsAboutTicker (String ticker) {


        NewsResponse newsResponse = new NewsResponse();
        token = "02b9e74dc8a54b8cb99ef52fa07cd062";
        String requestURL = "https://newsapi.org/v2/everything?apiKey="+token+"&q="+ticker;

        try {
            String jsonResponse = RestServiceUtil.restGETCall(requestURL);

            ObjectMapper mapper = new ObjectMapper();
            TickerNews tickerNews = mapper.readValue(jsonResponse, new TypeReference<TickerNews>() {});

            List<News> newsList = tickerNews.getArticles().stream().map(this::apply).collect(Collectors.toList());

            Collections.sort(newsList, (o1, o2) -> {
                String date1 = o1.getPublishedAt();
                String date2 = o2.getPublishedAt();
                return date1.compareTo(date2);
            });

            newsResponse.setSuccess(true);
            newsResponse.setResults(newsList.subList(0, 10));

            log.info("Total News Received for ticker [{}] - {}", ticker, tickerNews.getTotalResults());
        } catch (Exception e) {
            log.error("Error while processing the Company New Request for ticker {}", ticker, e);
        }

        return newsResponse;
    }

    private News apply (Article article) {
        News news = new News();
        news.setUrl(article.getUrl());
        news.setTitle(article.getTitle());
        news.setSource(article.getSourceName());
        news.setDescription(article.getDescription());
        news.setPublishedAt(article.getPublishedAt());
        news.setUrlToImage(article.getUrlToImage());

        return news;
    }
}
