package com.nisum.userws.controllers.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;

@Data
public class SignOutRequest implements Serializable {

    private static final long serialVersionUID = 1L;
    @NotNull
    @NotBlank
    private String username;
    @NotNull
    @NotBlank
    private String password;
}

