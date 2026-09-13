package com.granacerta.modules.authentication.web.dto;

import java.time.Instant;

public record AuthenticationResponse (
        String accessToken, Instant expiresIn
) {
}
