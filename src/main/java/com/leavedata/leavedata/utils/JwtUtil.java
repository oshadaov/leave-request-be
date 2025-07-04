package com.leavedata.leavedata.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@Component
public class JwtUtil {

    private static final String SECRET = "mySecretKeymySecretKeymySecretKeymySecretKeymySecretKeymySecretKey";
    private final long EXPIRATION = 1000 * 60 * 60 * 24;

    private final SecretKey key = Keys.hmacShaKeyFor(SECRET.getBytes());
    private final JwtParser parser = Jwts.parser().verifyWith(key).build();

    public String generateToken(String email, String role ,Long id,String name) {
        Key key = Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));
        Map<String, Object> claims = new HashMap<>();
        claims.put("email", email);
        claims.put("role", role);
        claims.put("id", id);
        claims.put("name",name);



        return Jwts.builder()
                .setClaims(claims)
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
                .signWith(key)
                .compact();
    }


    public String extractEmail(String token) {
        Claims claims = parser.parseSignedClaims(token).getPayload();
        return claims.getSubject();
    }
    public String extractName(String token) {
        Claims claims = parser.parseSignedClaims(token).getPayload();
        return claims.getSubject();
    }

    public String extractRole(String token) {
        Claims claims = parser.parseSignedClaims(token).getPayload();
        return claims.get("role", String.class);
    }
    public Long extractId(String token) {
        Claims claims = parser.parseSignedClaims(token).getPayload();
        return claims.get("id", Long.class);
    }

    public boolean isTokenValid(String token, String username) {
        return extractEmail(token).equals(username) && !isTokenExpired(token);
    }

    public boolean isTokenExpired(String token) {
        Date expiration = parser.parseSignedClaims(token).getPayload().getExpiration();
        return expiration.before(new Date());
    }

}
