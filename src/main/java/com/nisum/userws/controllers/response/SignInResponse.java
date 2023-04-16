package com.nisum.userws.controllers.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SignInResponse{
    private static final String TOKEN_TYPE = "Bearer";
    private String token;
}
