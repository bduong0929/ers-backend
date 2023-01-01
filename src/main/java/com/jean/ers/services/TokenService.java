package com.jean.ers.services;

import com.jean.ers.dtos.responses.Principal;
import com.jean.ers.utils.JwtConfig;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.Date;

@ApplicationScoped
public class TokenService {
    @Inject
    private JwtConfig jwtConfig;

    public String generateToken(Principal subject) {
        long now = System.currentTimeMillis();
        JwtBuilder tokenBuilder = Jwts.builder()
                .setId(subject.getId())
                .setIssuer("ers")
                .setIssuedAt(new Date(now))
                .setExpiration(new Date(now + jwtConfig.getExpiration()))
                .setSubject(subject.getUsername())
                .claim("email", subject.getEmail())
                .claim("givenName", subject.getGivenName())
                .claim("surname", subject.getSurname())
                .claim("role", subject.getRole())
                .signWith(jwtConfig.getSigAlg(), jwtConfig.getSigningKey());

        return tokenBuilder.compact();
    }

    public Principal extractRequesterDetails(String token) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(jwtConfig.getSigningKey())
                    .parseClaimsJws(token)
                    .getBody();

            return new Principal(
                    claims.getId(),
                    claims.getSubject(),
                    claims.get("email", String.class),
                    claims.get("givenName", String.class),
                    claims.get("surname", String.class),
                    claims.get("role", String.class)
            );
        } catch (Exception e) {
            return null;
        }
    }
}
