package com.example.springbootblogrestapi.security.jwt;

import com.example.springbootblogrestapi.exception.BlogAPIException;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenProvider {

    @Value("${app.jwt-secret}")
    private String jwtSecret;

    @Value("${app.jwt-expiration-milliseconds}")
    private int jwtExpirationInMs;

    // generate jwt token
    public String generateToken(
            Authentication authentication
    ) {
        String username = authentication.getName();
        Date currentDate = new Date();
        Date expirationDate = new Date(currentDate.getTime() + jwtExpirationInMs);

        String token = Jwts
                .builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();

        return token;
    }

    // get username from Jwt token
    public String getUsernameFromJwt(String token) {
        Claims claims = Jwts
                .parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }

    // validate jwt token
    public boolean validateToken(String token) {
        try {
            Jwts
                    .parser()
                    .setSigningKey(jwtSecret)
                    .parseClaimsJws(token);
            return true;
        } catch (SignatureException exception) {
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Invalid JWT signature");
        } catch (MalformedJwtException exception) {
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Invalid JWT token");
        } catch (ExpiredJwtException exception) {
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Expired JWT token");
        } catch (UnsupportedJwtException exception) {
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Unsupported JWT token");
        } catch (IllegalArgumentException exception) {
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "JWT claims string is empty");
        }
    }

}





























