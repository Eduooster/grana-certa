package com.granacerta.modules.authentication.web.dto;

public record LoginRequest(
        String email, String password
) {
}
