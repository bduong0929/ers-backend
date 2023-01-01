package com.jean.ers.utils;

import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.xml.bind.DatatypeConverter;

import javax.crypto.spec.SecretKeySpec;
import java.io.FileReader;
import java.io.IOException;
import java.security.Key;
import java.util.Properties;

@ApplicationScoped
public class JwtConfig {
    private final int expiration = 60 * 60 * 1000;
    private final SignatureAlgorithm sigAlg = SignatureAlgorithm.HS256;

    private final Key signingKey;
    private final Properties properties = new Properties();

    public JwtConfig() {
        try {
            properties.load(new FileReader("token.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        byte[] saltyBytes = DatatypeConverter.parseBase64Binary(properties.getProperty("salt"));
        signingKey = new SecretKeySpec(saltyBytes, sigAlg.getJcaName());
    }

    public int getExpiration() {
        return expiration;
    }

    public SignatureAlgorithm getSigAlg() {
        return sigAlg;
    }

    public Key getSigningKey() {
        return signingKey;
    }
}