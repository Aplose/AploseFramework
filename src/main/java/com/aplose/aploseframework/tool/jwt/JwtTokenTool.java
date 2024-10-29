package com.aplose.aploseframework.tool.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.nio.charset.StandardCharsets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import com.aplose.aploseframework.model.UserAccount;
import com.aplose.aploseframework.service.ConfigService;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import javax.crypto.SecretKey;
import java.time.Instant;

@Component
public class JwtTokenTool {

    @Autowired
    private ConfigService _configService;


    public String generateToken(UserAccount userDetails) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("userAccountId", userDetails.getId());
        return doGenerateToken(claims, userDetails.getUsername());
    }

    private Key getSigningKey() {
        byte[] keyBytes = this._configService.getStringConfig("aplose.framework.security.jwt.secretKey").getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    private String doGenerateToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .claims(claims)
                .subject(subject)
                .issuedAt(Date.from(Instant.now()))
                .expiration(Date.from(Instant.now().plusSeconds(60 * 60 * 10)))
                .signWith(getSigningKey())
                .compact();
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        if(claims == null){
            return null;
        }
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        Claims claims;
        try {
            claims = Jwts.parser()
                    .verifyWith((SecretKey)getSigningKey()).build()
                    .parseSignedClaims(token)
                    .getPayload();
        } catch(Exception e) {
            return null;
        }
        return claims;
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        if(username == null){
            return false;
        }
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private Boolean isTokenExpired(String token) {
        final Date expiration = extractClaim(token, Claims::getExpiration);
        return expiration.before(new Date());
    }
    
}
