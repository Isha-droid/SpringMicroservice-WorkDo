package com.task.user.user.config;

import java.util.Date;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import com.task.user.user.entity.UserPrincipal;

@Component
public class JwtTokenProvider {

    private String jwtSecret = "abcdefghijklmnopqrdt";

    private int jwtExpirationInMs = 360000;

    public String generateToken(Authentication authentication) {
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpirationInMs);

        return Jwts.builder()
                .setSubject(Long.toString(userPrincipal.getId()))
                .claim("firstName", userPrincipal.getFirstName())
                .claim("lastName", userPrincipal.getLastName())
                .claim("email", userPrincipal.getEmail())
                .claim("role", userPrincipal.getRole())
                .claim("enabled", userPrincipal.isEnabled())
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public Long getUserIdFromJWT(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();

        return Long.parseLong(claims.getSubject());
    }

    public boolean validateToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (Exception e) {
            // Handle token validation exceptions
        }
        return false;
    }

    public Claims getClaimsFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();
    }

    public String getFirstNameFromJWT(String token) {
        Claims claims = getClaimsFromToken(token);
        return claims.get("firstName", String.class);
    }

    public String getLastNameFromJWT(String token) {
        Claims claims = getClaimsFromToken(token);
        return claims.get("lastName", String.class);
    }

    public String getEmailFromJWT(String token) {
        Claims claims = getClaimsFromToken(token);
        return claims.get("email", String.class);
    }

    public String getRoleFromJWT(String token) {
        Claims claims = getClaimsFromToken(token);
        return claims.get("role", String.class);
    }

    public boolean getEnabledFromJWT(String token) {
        Claims claims = getClaimsFromToken(token);
        return claims.get("enabled", Boolean.class);
    }
}
