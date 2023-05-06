package com.edu.msu.stockanalysis.model;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class HistChart {
    private long Date;
    private Double open;
    private Double high;
    private Double low;
    private Double close;
    private Long volume;
}
