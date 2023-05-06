package com.edu.msu.stockanalysis.model;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class DailyChart {
    private Long date;
    private Double close;

}
