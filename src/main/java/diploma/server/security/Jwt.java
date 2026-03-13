package diploma.server.security;

import java.util.Date;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import diploma.server.config.ServerConfigurationProperties;
import diploma.server.exception.ConfigurationException;

@Service
public class Jwt {
    
    private String SECRET;

    public Jwt(ServerConfigurationProperties confProps) {
        if (confProps.getJwtSecret().trim().length() == 0) {
            throw new ConfigurationException("The jwtSecret must be valid.");
        }
        this.SECRET = confProps.getJwtSecret();
    }

 
    public String genareteToken(String username, Map<String, Object> claims) {
        return createToken(claims, username);
    }

    private String createToken(Map<String, Object> claims, String username) {
        return Jwts.builder()
            .claims(claims)
            .issuer("ZDiploma")
            .subject(username)
            .issuedAt(new Date())
            .expiration(new Date(System.currentTimeMillis() + Long.valueOf("31557600000"))) // 1 year
            .signWith(getSignKey())
            .compact();
    }

    private SecretKey getSignKey() {
        SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(this.SECRET));
        return key;
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExparation(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public Claims extractAllClaims(String token) throws JwtException {
        try {
            return (Claims) Jwts.parser()
                .verifyWith(getSignKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
        } catch (JwtException e) {
            return null;
        }
    }
}
