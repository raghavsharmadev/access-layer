package com.rs.accesslayer.utitlity;

import com.rs.accesslayer.entity.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class JwtUtility {

    private static final String SECRET_KEY = "a8f9c2d4e7b1a6c9d3f8e2a1b5c7d9f0a4e6b8c1d2f3a5b7c9d1e2f4a6b8c0d2"; // Use a secure key from config

    public static String generateToken(final User user) {
        return Jwts.builder()
                .setSubject(user.getEmail())
                .claim("userId", user.getId())
                .claim("role", user.getRole())
                .claim("tenantId", user.getTenantId())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10 hours
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    public static Long extractUserId(final String token) {
        Claims claims = Jwts.parser().setSigningKey(SECRET_KEY).build().parseSignedClaims(token).getPayload();
        return claims.get("userId", Long.class);
    }

    public static String extractRole(final String token) {
        Claims claims = Jwts.parser().setSigningKey(SECRET_KEY).build().parseSignedClaims(token).getPayload();
        return claims.get("role", String.class);
    }

    public static Long extractTenantId(final String token) {
        Claims claims = Jwts.parser().setSigningKey(SECRET_KEY).build().parseSignedClaims(token).getPayload();
        return claims.get("tenantId", Long.class);
    }

    public static boolean validateToken(final String token) {
        try {
            Jwts.parser().setSigningKey(SECRET_KEY).build().parseSignedClaims(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
