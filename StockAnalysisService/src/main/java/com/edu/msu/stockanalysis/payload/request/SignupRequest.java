package com.edu.msu.stockanalysis.payload.request;

import com.edu.msu.stockanalysis.model.ERole;
import lombok.Data;
import lombok.ToString;

import java.util.Set;

@Data
@ToString
public class SignupRequest {

    private String username;
    private String password;
    private String email;
    private Set<String> role;
}
