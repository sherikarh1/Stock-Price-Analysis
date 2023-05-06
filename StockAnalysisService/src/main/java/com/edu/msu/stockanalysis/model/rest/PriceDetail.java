package com.edu.msu.stockanalysis.model.rest;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
public class PriceDetail {

    private String ticker;
    private String timestamp;
    private Date lastSaleTimestamp;
    private Date quoteTimestamp;
    private Double open;
    private Double high;
    private Double low;
    private Double mid;
    private Double tngoLast;
    private Double last;
    private Double lastSize;
    private Double bidSize;
    private Double bidPrice;
    private Double askPrice;
    private Double askSize;
    private Long volume;
    private Double prevClose;
}
