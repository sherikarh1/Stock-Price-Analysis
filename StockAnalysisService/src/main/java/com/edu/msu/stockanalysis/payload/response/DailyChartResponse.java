package com.edu.msu.stockanalysis.payload.response;

import com.edu.msu.stockanalysis.model.DailyChart;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class DailyChartResponse {

    private int total;
    private List<DailyChart> results;
}
