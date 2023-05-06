package com.edu.msu.stockanalysis.model.rest;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class CompanyDetails {
    private String ticker;
    private String name;
    private String description;
    private String startDate;
    private String endDate;
    private String exchangeCode;

}
