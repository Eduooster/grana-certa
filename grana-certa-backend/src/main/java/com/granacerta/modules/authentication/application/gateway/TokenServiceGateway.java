package com.granacerta.modules.authentication.application.gateway;

import com.granacerta.modules.authentication.application.usecase.AuthenticationContext;
import com.granacerta.security.jwt.TokenData;

import java.time.Instant;

public interface TokenServiceGateway {

    String generateToken(AuthenticationContext user );

    Instant expiryTime();

   TokenData validateAndExtract(String token);
}
