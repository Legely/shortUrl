package com.legely.shortUrl.service;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public class JwtService {

    @Value("${app.jwt.secret:myVerySecretKeyThatMustBeAtLeast32BytesLongForSecurity}")
    private String jwtSecret;

    @Value("${app.jwt.expiration-ms:86400000}")
    private long jwtExpirationMs;


    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));
    };


    public String generateJwtToken(String nickname){
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpirationMs);

        return Jwts.builder()
                .subject(nickname)                 // Кому принадлежит токен
                .issuedAt(now)                  // Время выдачи
                .expiration(expiryDate)         // Время истечения
                .signWith(getSigningKey())      // Подпись секретным ключом
                .compact();
    }
}
