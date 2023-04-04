package com.nisum.userws.utils.request;

import javax.validation.constraints.*;
import lombok.Data;

@Data
public class PhoneRequest {
    @NotNull
    @NotBlank
    private String  number;

    @NotNull
    @NotBlank
    private String  citycode;

    @NotNull
    @NotBlank
    private String  contrycode;

}

