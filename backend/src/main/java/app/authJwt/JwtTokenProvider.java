package app.authJwt;

import app.dto.UserDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.Value;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;

@Component
public class JwtTokenProvider implements AuthenticationProvider {

    private final String jwtSecret;
    private final int jwtExpirationInMs;

    public JwtTokenProvider(JwtConfig jwtConfig) {
        this.jwtExpirationInMs = jwtConfig.getJwtExpirationInMs();
        this.jwtSecret = jwtConfig.getJwtSecret();
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String token = (String) authentication.getCredentials();
        if (!validateToken(token)) {
            return null;
        }

        UserDTO userDate = getUserDate(token);

        String userName = userDate.getUsername();

        return new UsernamePasswordAuthenticationToken(userName, token);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

    public String generateToken(UserDTO userDate) {
        Date expirationDate = new Date(System.currentTimeMillis() + jwtExpirationInMs);
        SecretKey secretKey = Keys.hmacShaKeyFor(jwtSecret.getBytes());

        return Jwts.builder()
                .claim("userDate", userDate)
                .setIssuedAt(new Date())
                .setExpiration(expirationDate)
                .signWith(secretKey)
                .compact();
    }

    public UserDTO getUserDate(String token){
        Jws<Claims> claimsJws = Jwts.parserBuilder()
                .setSigningKey(getSecretKey())
                .build()
                .parseClaimsJws(token);

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.convertValue(claimsJws.getBody().get("userDate"), UserDTO.class);
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(getSecretKey())
                    .build()
                    .parseClaimsJws(token);


            return true;
        } catch (Exception ex) {

            System.out.println("Error al validar el token: " + ex.getMessage());
            return false;
        }
    }

    private Key getSecretKey() {
        return Keys.hmacShaKeyFor(jwtSecret.getBytes());
    }
}
