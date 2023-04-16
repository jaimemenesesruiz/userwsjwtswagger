package com.nisum.userws.advice;

import com.nisum.userws.exception.ApiException;
import com.nisum.userws.controllers.response.MessageResponse;
import com.nisum.userws.security.AuthTokenFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class ControllerAdvice {
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthTokenFilter.class);

    @ExceptionHandler(value = ApiException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public MessageResponse handleException(ApiException ex, WebRequest request) {
        LOGGER.info("ClaseError: "+ex.getClass());
        return new MessageResponse(ex.getMessage());
    }

    @ExceptionHandler(value = {AccessDeniedException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public MessageResponse handleException2(Exception ex, WebRequest request) {
        LOGGER.info("ClaseError: "+ex.getClass());
        return new MessageResponse(ex.getMessage());
    }

    @ExceptionHandler(value = {Exception.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public MessageResponse handleException3(Exception ex, WebRequest request) {
        LOGGER.info("ClaseError: "+ex.getClass());
        return new MessageResponse(ex.getMessage());
    }
}
