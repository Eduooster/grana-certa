package com.granacerta.security.jwt;

import java.time.Instant;

public record AuthenticationToken(
        String accessToken, Instant expiresIn
) {
}
