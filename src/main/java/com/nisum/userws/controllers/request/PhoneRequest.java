package com.nisum.userws.controllers.request;

import javax.validation.constraints.*;
import lombok.Data;

@Data
public class PhoneRequest {
    @NotNull
    @NotBlank
    @Pattern(regexp = "[0-9]+", message = "debe tener solo  numeros")
    private String  number;

    @NotNull
    @NotBlank
    @Pattern(regexp = "[0-9]+", message = "debe tener solo  numeros")
    private String  citycode;

    @NotNull
    @NotBlank
    @Pattern(regexp = "[0-9]+", message = "debe tener solo  numeros")
    private String  contrycode;

}

