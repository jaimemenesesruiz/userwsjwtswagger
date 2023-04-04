package com.nisum.userws.utils.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nisum.userws.utils.response.MessageResponse;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Component;

import java.beans.Beans;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class AuthEntryPointJwt implements AuthenticationEntryPoint {
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthEntryPointJwt.class);

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException e) throws IOException, ServletException {
        LOGGER.error("Usuario no autorizado: {}", e.getMessage());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        final var mapper = new ObjectMapper();
        mapper.writeValue(response.getOutputStream(), new MessageResponse("Usuario no tiene suficientes privilegios"));
    }
}

