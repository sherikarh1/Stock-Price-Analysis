package com.edu.msu.stockanalysis.model;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class CompanySearchResult {
    private String name;
    private String ticker;

}
