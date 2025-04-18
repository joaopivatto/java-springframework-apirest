package com.joao.pivatto.apirest.infrastructure;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.joao.pivatto.apirest.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    public String generateToken(User user) {
        try{
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.create().
                    withIssuer("joaopivatto-apirest").
                    withSubject(user.getLogin()).
                    withExpiresAt(genarateExpirationDate()).
                    sign(algorithm);
        }catch (JWTCreationException jwtCreationException){
            throw new RuntimeException("Error while generating token", jwtCreationException);
        }
    }

    public String validateToken(String token) {
        try{
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm).
                    withIssuer("joaopivatto-apirest").
                    build().
                    verify(token).
                    getSubject();
        }catch (JWTVerificationException jwtVerificationException){
            return "";
        }
    }

    private Instant genarateExpirationDate(){
        return LocalDateTime.now().plusHours(1).toInstant(ZoneOffset.of("-03:00"));
    }
}
