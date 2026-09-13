package com.granacerta.security.jwt;

import java.util.UUID;

public record TokenData(UUID userId,
                        String email,
                        String role) {
}
