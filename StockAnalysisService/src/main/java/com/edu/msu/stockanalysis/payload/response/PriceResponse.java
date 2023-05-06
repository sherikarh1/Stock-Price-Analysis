package com.edu.msu.stockanalysis.payload.response;

import com.edu.msu.stockanalysis.model.CompanyPrice;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Data
@ToString
public class PriceResponse {

    private List<CompanyPrice> results = new ArrayList<>();
    private boolean success;
}
