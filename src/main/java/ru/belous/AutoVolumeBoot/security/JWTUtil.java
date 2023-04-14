package ru.belous.AutoVolumeBoot.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import java.time.ZonedDateTime;
import java.util.Date;

@Component
public class JWTUtil {
    @Value("${jwt_secret}")
    private String secret;
    public String generateToken(String username){
        Date expirationDate = Date.from(ZonedDateTime.now().plusMinutes(60).toInstant());
        return JWT.create()
                .withSubject("Userdetails")
                .withClaim("username",username)
                .withIssuedAt(new Date())
                .withIssuer("Snvkez")
                .withExpiresAt(expirationDate)
                .sign(Algorithm.HMAC256(secret));
    }

    public String validTokenAndRetrieveSubject(String token) throws JWTVerificationException {
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(secret))
                .withSubject("Userdetails")
                .withIssuer("Snvkez")
                .build();
        DecodedJWT decodedJWT = jwtVerifier.verify(token);
        return decodedJWT.getClaim("username").asString();
    }
}
