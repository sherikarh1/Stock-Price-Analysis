package com.edu.msu.stockanalysis.model.rest;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
public class HistoricalChart {
    private Date date;
    private Double close;
    private Double high;
    private Double low;
    private Double open;
    private Long volume;
    private Double adjClose;
    private Double adjHigh;
    private Double adjLow;
    private Double adjOpen;
    private Long adjVolume;
    private Double divCash;
    private Double splitFactor;
}
