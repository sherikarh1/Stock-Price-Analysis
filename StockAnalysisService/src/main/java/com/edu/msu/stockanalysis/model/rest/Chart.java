package com.edu.msu.stockanalysis.model.rest;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
public class Chart {
    private Date date;
    private Double close;
    private Double high;
    private Double low;
    private Double open;
}
