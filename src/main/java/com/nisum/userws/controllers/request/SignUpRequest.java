package com.nisum.userws.controllers.request;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import lombok.Data;

import java.util.List;

@Data
public class SignUpRequest {
    @NotNull
    @NotBlank
    private String  name;

    @NotNull
    @NotBlank
    @Pattern(regexp = "^(([^<>()\\[\\]\\\\.,;:\\s@”]+(\\.[^<>()\\[\\]\\\\.,;:\\s@”]+)*)|(“.+”))@((\\[[0–9]{1,3}\\.[0–9]{1,3}\\.[0–9]{1,3}\\.[0–9]{1,3}])|(([a-zA-Z\\-0–9]+\\.)+[a-zA-Z]{2,}))$" , message = " deber tener el formato aaaaaaa@dominio.cl")
    private String  email;

    @NotNull
    @NotBlank
    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{4,8}$", message = " deber tener entre 4 y 8 caracteres entre mayusculas, minusculas y numeros")
    private String  password;

    @Valid
    private List<PhoneRequest> phones;

}

