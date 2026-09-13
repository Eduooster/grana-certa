package com.granacerta.security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.granacerta.modules.authentication.application.gateway.TokenServiceGateway;
import com.granacerta.modules.authentication.application.usecase.AuthenticationContext;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

@Service
public class JwtService implements TokenServiceGateway {

    private final Algorithm algorithm;
    private final JWTVerifier verifier;

    public JwtService(
            @Value("${api.security.token.secret}") String secret
    ) {
        this.algorithm = Algorithm.HMAC256(secret);

        this.verifier = JWT.require(algorithm)
                .withIssuer("API GranaCerta")
                .build();
    }
    @Override
    public String generateToken(AuthenticationContext user) {
        return JWT.create()
                .withIssuer("API GranaCerta")
                .withSubject(user.userId().toString())
                .withClaim("email", user.email())
                .withClaim("role", user.role().name())
                .withExpiresAt(expiryTime())
                .sign(algorithm);
    }

    @Override
    public Instant expiryTime() {
        return Instant.now().plus(20, ChronoUnit.HOURS);
    }

    @Override
    public TokenData validateAndExtract(String token) {
        DecodedJWT jwt = verifier.verify(token);

        return new TokenData(
                UUID.fromString(jwt.getSubject()),
                jwt.getClaim("email").asString(),
                jwt.getClaim("role").asString()
        );
    }
}
