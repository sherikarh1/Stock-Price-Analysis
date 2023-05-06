package com.edu.msu.stockanalysis.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
@EqualsAndHashCode
public class CompanyPrice {

    private String ticker;
    private String timestamp;
    private Double last;
    private Double prevClose;
    private Double open;
    private Double high;
    private Double low;
    private Double mid;
    private Long volume;
    private Double bidSize;
    private Double bidPrize;
    private Double askSize;
    private Double askPrice;

}
