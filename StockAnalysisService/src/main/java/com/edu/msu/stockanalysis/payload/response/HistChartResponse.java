package com.edu.msu.stockanalysis.payload.response;

import com.edu.msu.stockanalysis.model.HistChart;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class HistChartResponse {
    private int total;
    private List<HistChart> results;
    private boolean success;
}
