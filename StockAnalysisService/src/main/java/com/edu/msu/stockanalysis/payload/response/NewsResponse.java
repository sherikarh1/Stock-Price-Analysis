package com.edu.msu.stockanalysis.payload.response;

import com.edu.msu.stockanalysis.model.News;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class NewsResponse {
    private List<News> results;
    private boolean success;
}
