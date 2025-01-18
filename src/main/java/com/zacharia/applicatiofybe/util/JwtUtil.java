package com.zacharia.applicatiofybe.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;


@Component
public class JwtUtil {
    @Value("${JWT_SECRET}")
    private String secret;

    @Value("${JWT_EXPIRATION}")
    private long expiration;

    //Step 1:
    public SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(secret.getBytes());
    }


    //Step 2:
    // Updated method to use parserBuilder() and verifyWith()
    private Claims extractAllClaims(String token) {
//        System.out.println("#############" + token);
//        System.out.println("%%%%%%%%%%%%" + secret);

        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token) // Use parseSignedClaims
                .getPayload(); // use getPayload instead of getBody
    }

    //Step 3:
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    //Step 4:
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);

    }

    //Step 5:
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    //Step 6:
    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    //Step 7:
    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .claims(claims)
                .subject(subject)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSigningKey(), Jwts.SIG.HS256)
                .compact();
    }


    //Step 8:
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, userDetails.getUsername());
    }

    //Step 9:
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

}
