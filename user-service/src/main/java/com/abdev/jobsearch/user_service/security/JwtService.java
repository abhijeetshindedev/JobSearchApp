package com.abdev.jobsearch.user_service.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.abdev.jobsearch.user_service.Entity.Role;
import com.abdev.jobsearch.user_service.Entity.User;

import java.security.Key;
import java.util.Date;

@Component
@Service
//@ConfigurationProperties(prefix = "jwt")
public class JwtService {


    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private long expiration;

    private Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateToken(UserDetails userDetails) {
        System.out.println("secret : "+secret);
        System.out.println("expiration : "+expiration);
        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(Keys.hmacShaKeyFor(secret.getBytes()), SignatureAlgorithm.HS256)
                .compact();
    }

    // public String generateToken(User user) {
    //     return Jwts.builder()
    //             .setSubject(String.valueOf(user.getId())) // ✅ userId
    //             .claim("email", user.getEmail())
    //             .claim("roles", user.getRoles().stream()
    //                     .map(Role::getName)
    //                     .toList())
    //             .setIssuedAt(new Date())
    //             .setExpiration(new Date(System.currentTimeMillis() + expiration))
    //             .signWith(getSignKey(), SignatureAlgorithm.HS256)
    //             .compact();
    // }

    public String extractUsername(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secret.getBytes())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}
