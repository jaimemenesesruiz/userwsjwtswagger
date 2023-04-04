package com.nisum.userws.utils.request;

import javax.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SignInRequest  {
    @NotBlank
    private String username;
    @NotBlank
    private String password;
}