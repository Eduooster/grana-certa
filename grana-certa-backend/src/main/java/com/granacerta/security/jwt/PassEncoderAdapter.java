package com.granacerta.security.jwt;

import com.granacerta.modules.authentication.application.gateway.PasswordGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PassEncoderAdapter implements PasswordGateway {
    private final PasswordEncoder passwordEncoder;

    @Override
    public String hash(String password) {
        return passwordEncoder.encode(password);
    }

    @Override
    public boolean matches(String password, String hashPassword) {
        return passwordEncoder.matches(password, hashPassword);
    }
}
