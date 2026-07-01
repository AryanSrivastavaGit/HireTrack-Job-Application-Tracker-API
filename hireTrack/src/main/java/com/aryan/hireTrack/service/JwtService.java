package com.aryan.hireTrack.service;


import com.aryan.hireTrack.dto.TokenPair;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class JwtService {
    @Value("${app.jwt.secret}")
    private String jwtSecret;
    @Value("${app.jwt.expiration}")
    private long jwtExpirationMs;
    @Value("${app,jwt.refresh-expiration}")
    private long refreshExpirationMs;

    //generate access token
    public String generateAccessToken(Authentication authentication){
        return generateToken(authentication, jwtExpirationMs, new HashMap<>());
    }

    //generate refresh token
    public String generateRefreshToken(Authentication authentication){

        Map<String, String> claims = new HashMap<>();
        claims.put("tokenType", "refresh");

        return generateToken(authentication, refreshExpirationMs, claims);
    }

    private String generateToken(Authentication authentication, long expiration, Map<String, String> claims) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        Date now = new Date(); // time of token creation
        Date expiryDate = new Date(now.getTime() + expiration); // time of token expiration

        return Jwts.builder()
                .header()
                .add("typ", "JWT")
                .and()
                .subject(userDetails.getUsername())
                .claims(claims)
                .issuedAt(now)
                .expiration(expiryDate)
                .signWith(getSignInKey())
                .compact();
    }

    private SecretKey getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(jwtSecret);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    // validate token
    public boolean isValidToken(String token, UserDetails userDetails){
        final String email = extractEmail(token); // extract email from token
        return email!=null && email.equals(userDetails.getUsername());
    }

    public String extractEmail(String token){
        return Jwts.parser()
                .verifyWith(getSignInKey())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    // validate if token is refresh token
    public boolean isRefreshToken(String token){
        Claims claims = Jwts.parser()
                .verifyWith(getSignInKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
        if(claims==null){
            return false;
        }
        return "refresh".equals(claims.get("tokenType"));
    }

    public TokenPair generateTokenPair(Authentication authentication) {
        String accessToken = generateAccessToken(authentication);
        String refreshToken = generateRefreshToken(authentication);
        return new TokenPair(accessToken, refreshToken);
    }
}