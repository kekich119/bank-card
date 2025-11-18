package com.example.bankcards.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
@Data
public class JWT {

    @Value("${bank_rest-main.app.secret}")
    private String secret;

    @Value("${bank_rest-main.app.expirationMs}")
    private int lifeTime;


    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(secret.getBytes());
    }

    public String generateToken(Authentication auth) {  //this method generate jwt token
        UserDetailsImpl userDetails = (UserDetailsImpl) auth.getPrincipal(); //get info from auth user
        return Jwts.builder()
                .subject(userDetails.getUsername())
                .id(String.valueOf(userDetails.getId()))
                .claim("roleId", userDetails.getRoleId())
                .claim("email", userDetails.getEmail())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + lifeTime))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();

    }



    public String getEmailFromToken(String token) {

        Claims claims = Jwts.parser()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.get("email", String.class);
    }

    public String getNameFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public String getToken(HttpServletRequest request) {
        String token = null;

        if (request.getCookies() != null) {
            for (var cookie : request.getCookies()) {
                if (cookie.getName().equals("JWT")) {
                    token = cookie.getValue();
                    break;
                }
            }
        }
        return token;
    }








}
