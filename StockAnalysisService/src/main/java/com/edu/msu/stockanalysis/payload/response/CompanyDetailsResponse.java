package com.edu.msu.stockanalysis.payload.response;

import com.edu.msu.stockanalysis.model.rest.CompanyDetails;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Data
@ToString
@EqualsAndHashCode
public class CompanyDetailsResponse {
    private List<CompanyDetails> results = new ArrayList<>();
    private boolean success;

}
