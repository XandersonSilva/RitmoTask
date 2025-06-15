package edu.xanderson.ritimoTask.infra.security;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;

import edu.xanderson.ritimoTask.model.entity.UserEntity;

@Service
public class TokenService {
    @Value("${token.secret.key}")
    private String secretKey;
    
    public String generateToken(UserEntity user){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secretKey);
            String token = JWT.create()
                                .withIssuer("auth-api")
                                .withSubject(user.getEmail())
                                .withExpiresAt(genExpirationDate())
                                .sign(algorithm);
            return token;
        } catch (JWTCreationException e) {
            throw new RuntimeException("Error while generating token\n", e);
        }
    }

    public String validateToken(String token){
         try {
            Algorithm algorithm = Algorithm.HMAC256(secretKey);
            return JWT.require(algorithm)
                                .withIssuer("auth-api")
                                .build()
                                .verify(token)
                                .getSubject();
                                
        } catch (JWTCreationException e) {
            return "";
        }
    }


    private Instant genExpirationDate(){
        return LocalDateTime.now().plusHours(3).toInstant(ZoneOffset.of("-03:00"));
    }
}
