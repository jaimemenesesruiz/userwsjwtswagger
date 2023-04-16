package com.nisum.userws.security;

import com.nisum.userws.services.impl.UserDetailsImpl;
import io.jsonwebtoken.*;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Log
public class JwtUtils {

    @Value("${userws.app.jwtSecret}")
    private String jwtSecret;

    @Value("${userws.app.jwtExpirationMs}")
    private int jwtExpirationMs;

    public String generateJwtToken(UserDetailsImpl userPrincipal) {
        return generateTokenFromUsername(userPrincipal.getUsername());
    }

    public String generateTokenFromUsername(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public String getUsernameFromJwtToken(String token) {
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateJwtToken(String token) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        } catch (SignatureException e) {
            log.severe("Firma del token Invalida: {}"+ e.getMessage());
        } catch (MalformedJwtException e) {
            log.severe("Token Invalido: " + e.getMessage());
        } catch (ExpiredJwtException e) {
            log.severe("Token ha expirado: " + e.getMessage());
        } catch (UnsupportedJwtException e) {
            log.severe("Token ha sido alterado: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            log.severe("Token esta vacío: " + e.getMessage());
        }
        return false;
    }
}

