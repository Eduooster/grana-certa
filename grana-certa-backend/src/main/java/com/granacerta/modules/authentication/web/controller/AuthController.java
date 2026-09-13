package com.granacerta.modules.authentication.web.controller;

import com.granacerta.modules.authentication.application.command.LoginUserCommand;
import com.granacerta.modules.authentication.application.command.RegisterUserCommand;
import com.granacerta.security.jwt.AuthenticationToken;
import com.granacerta.modules.authentication.application.usecase.LoginUseCase;
import com.granacerta.modules.authentication.application.usecase.RegisterUserLocalUseCase;
import com.granacerta.modules.authentication.web.dto.AuthenticationResponse;
import com.granacerta.modules.authentication.web.dto.LoginRequest;

import com.granacerta.modules.authentication.web.dto.RegisterUserRequest;
import com.granacerta.modules.authentication.web.mapper.AuthenticationWebMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping ("/api/v1/auth")
@RequiredArgsConstructor

public class AuthController {

    private final LoginUseCase loginUseCase;
    private final RegisterUserLocalUseCase registerUserLocalUseCase;
    private final AuthenticationWebMapper authenticationWebMapper;

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@Valid @RequestBody LoginRequest request) {
        LoginUserCommand command = authenticationWebMapper.toCommand(request);
        AuthenticationToken result = loginUseCase.execute(command);
        AuthenticationResponse response  = authenticationWebMapper.toResponse(result);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/register/local")
    public ResponseEntity<AuthenticationResponse> register(@Valid @RequestBody RegisterUserRequest request) {

        RegisterUserCommand command = authenticationWebMapper.toRegisterCommand(request);
        AuthenticationToken result = registerUserLocalUseCase.execute(command);
        AuthenticationResponse response  = authenticationWebMapper.toResponse(result);
        return ResponseEntity.ok(response);

    }




}
