package com.granacerta.modules.authentication.application.result;

import java.time.Instant;

public record AuthenticationTokenResult (String accessToken, Instant expiresIn) {
}
