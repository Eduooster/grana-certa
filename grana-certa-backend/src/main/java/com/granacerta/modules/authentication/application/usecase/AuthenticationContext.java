package com.granacerta.modules.authentication.application.usecase;

import com.granacerta.modules.user.domain.entity.UserRole;

import java.util.UUID;

public record AuthenticationContext(
        UUID userId,
        String email,
        UserRole role
) {}