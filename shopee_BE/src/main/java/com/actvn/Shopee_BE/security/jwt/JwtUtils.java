package com.actvn.Shopee_BE.security.jwt;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import org.slf4j.Logger;

@Slf4j
@Component
public class JwtUtils {

//    private final static Logger logger = LoggerFactory.getLogger(JwtUtils.class);

    @Value("${spring.app.jwtSecret}")
    private String jwtSecretKey;
    @Value("${spring.app.jwtExpiration}")
    private long jwtExpiration;

    public String generateJWTTokenFromUsername(UserDetails userDetails) {
        String username = userDetails.getUsername();
        String password = userDetails.getPassword();

        long expiration= (new Date()).getTime() + jwtExpiration;
        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date())
                .expiration(new Date(expiration))
                .signWith(key())
                .compact();
    }

    private Key key(){
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecretKey));
    }

    public String getUsernameFromJwtToken(String token) {
        return Jwts.parser()
                .verifyWith((SecretKey) key())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    //lay token tu header client gui ve
    public String getJwtFromHeader(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        log.debug("Authorization: header: {}", bearerToken);
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7); // remove "Bearer" prefix
        }
        return null;
    }

    public boolean validateJwtToken(String authToken){
        log.debug("validate jwt token", authToken);
        try {
            Jwts.parser()
                    .verifyWith((SecretKey) key())
                    .build()
                    .parseSignedClaims(authToken);
            return true;
        }catch (MalformedJwtException exception){
            log.error("Invalid  JWT token: {}", exception.getMessage());
        }catch (ExpiredJwtException exception){
            log.error("JWT token is expired: {}", exception.getMessage());
        }catch (UnsupportedJwtException exception){
            log.error("JWT is unsupported: {}", exception.getMessage());
        }catch (IllegalArgumentException exception){
            log.error("JWT claims string is empty: {}", exception.getMessage());
        }
        return false;
    }


}
