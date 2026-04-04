package com.rs.accesslayer.utitlity;

import com.rs.accesslayer.entity.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtUtility {

    private static String SECRET_KEY; // Use a secure key from config

    @Value("${jwt.secret}")
    private void setSecretKey(String secretKey) {
        SECRET_KEY = secretKey;
    }

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
