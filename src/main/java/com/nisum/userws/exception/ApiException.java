package com.nisum.userws.exception;

public class ApiException  extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ApiException(String token, String message) {
        super(String.format("Ha fallado por [%s]: %s", token, message));
    }
}
