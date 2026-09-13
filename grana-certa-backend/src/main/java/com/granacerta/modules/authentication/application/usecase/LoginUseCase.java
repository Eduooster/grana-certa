package com.granacerta.modules.authentication.application.usecase;

import com.granacerta.modules.authentication.application.command.LoginUserCommand;
import com.granacerta.modules.authentication.application.gateway.PasswordGateway;
import com.granacerta.modules.authentication.application.gateway.TokenServiceGateway;
import com.granacerta.modules.authentication.domain.entity.UserCredential;
import com.granacerta.modules.authentication.domain.exception.InvalidCredentialsException;
import com.granacerta.modules.authentication.domain.repository.UserCredentialRepository;
import com.granacerta.modules.user.domain.entity.User;
import com.granacerta.modules.user.domain.exception.UserNotFoundException;
import com.granacerta.modules.user.domain.repository.UserRepository;
import com.granacerta.security.jwt.AuthenticationToken;

public class LoginUseCase {

    private final PasswordGateway passwordGateway;
    private final TokenServiceGateway tokenServiceGateway;

    private final UserCredentialRepository userCredentialRepository;
    private final UserRepository userRepository;

    public LoginUseCase(PasswordGateway passwordGateway, TokenServiceGateway tokenServiceGateway, UserCredentialRepository userCredentialRepository, UserRepository userRepository) {
        this.passwordGateway = passwordGateway;
        this.tokenServiceGateway = tokenServiceGateway;
        this.userCredentialRepository = userCredentialRepository;
        this.userRepository = userRepository;
    }

    public AuthenticationToken execute(LoginUserCommand command) {

        User user = userRepository.findByEmailAndActiveTrue(command.email()).orElseThrow(() -> new UserNotFoundException(command.email()));


        UserCredential credential = userCredentialRepository.findByUserId(user.getId())
                .orElseThrow(() -> new InvalidCredentialsException("Invalid credentials"));


        if (!passwordGateway.matches(
                command.password(),
                credential.getPasswordHash()
        )) {
            throw new InvalidCredentialsException("Invalid email or password");
        }



        AuthenticationContext context = new AuthenticationContext(
                user.getId(),
                user.getEmail(),
                user.getUserRole()
        );

        String token = tokenServiceGateway.generateToken(context);


        return new AuthenticationToken(
                token,
                tokenServiceGateway.expiryTime()
        );
    }


}
