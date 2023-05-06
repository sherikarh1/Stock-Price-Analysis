package com.edu.msu.stockanalysis.payload.response;

import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class JwtResponse {

    private String token;
    private Long type;
    private Long id;
    private String username;
    private String email;

    private List<String> roles;

    public JwtResponse(String token, Long id, String username, String email, List<String> roles) {
        this.token = token;
        this.id = id;
        this.username = username;
        this.email = email;
        this.roles = roles;
    }
}
