package com.edu.msu.stockanalysis.payload.response;

import com.edu.msu.stockanalysis.model.CompanySearchResult;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Data
@ToString
public class SearchResponse {
    private int total;
    private List<CompanySearchResult> results = new ArrayList<>();

}
