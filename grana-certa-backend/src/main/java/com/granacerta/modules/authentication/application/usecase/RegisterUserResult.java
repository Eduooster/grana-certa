package com.granacerta.modules.authentication.application.usecase;

import com.granacerta.modules.user.domain.enums.UserRole;

public record RegisterUserResult(
        Long id, String email, String name, UserRole role, boolean active
) {
}