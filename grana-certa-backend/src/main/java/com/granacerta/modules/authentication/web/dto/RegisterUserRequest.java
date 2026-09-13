package com.granacerta.modules.authentication.web.dto;

public record RegisterUserRequest(
        String email, String password,String name) {
}
