package com.granacerta.modules.authentication.application.usecase;

import java.time.Instant;

public record LoginUserResult(
        String accessToken, Instant expiresIn

) {
}
