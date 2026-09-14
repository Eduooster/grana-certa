package com.granacerta.modules.authentication.application.usecase;

import com.granacerta.modules.authentication.application.command.RegisterUserCommand;
import com.granacerta.modules.authentication.application.gateway.PasswordGateway;
import com.granacerta.modules.authentication.application.gateway.TokenServiceGateway;
import com.granacerta.modules.authentication.domain.entity.UserCredential;
import com.granacerta.modules.authentication.domain.repository.UserCredentialRepository;
import com.granacerta.modules.financialAccount.domain.entity.FinancialAccount;

import com.granacerta.modules.financialAccount.domain.repository.FinancialAccountRepository;
import com.granacerta.modules.user.domain.entity.User;
import com.granacerta.modules.user.domain.enums.UserRole;
import com.granacerta.modules.user.domain.repository.UserRepository;
import com.granacerta.security.jwt.AuthenticationToken;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
public class RegisterUserLocalUseCase {

    private final UserRepository userRepository;
    private final PasswordGateway passwordGateway;
    private final UserCredentialRepository userCredentialRepository;

    private final TokenServiceGateway tokenServiceGateway   ;
    private final FinancialAccountRepository financialAccountRepository;

    public RegisterUserLocalUseCase(UserRepository userRepository, PasswordGateway passwordGateway, UserCredentialRepository userCredentialRepository, TokenServiceGateway tokenServiceGateway, FinancialAccountRepository financialAccountRepository) {
        this.userRepository = userRepository;
        this.passwordGateway = passwordGateway;
        this.userCredentialRepository = userCredentialRepository;


        this.tokenServiceGateway = tokenServiceGateway;
        this.financialAccountRepository = financialAccountRepository;
    }

    @Transactional
    public AuthenticationToken execute(RegisterUserCommand command) {

        User user = User.create(
                command.name(),
                command.email(),
                 UserRole.USER
        );

        User savedUser = userRepository.save(user);


        UserCredential credential = UserCredential.createLocal(
                passwordGateway.hash(command.password()),
                savedUser.getId()
        );


        userCredentialRepository.save(credential);

        FinancialAccount financialAccount = FinancialAccount.createDefaultWallet(
                savedUser.getId()
        );
        financialAccountRepository.save(financialAccount);

        AuthenticationContext context = new AuthenticationContext(
                savedUser.getId(),
                savedUser.getEmail(),
                savedUser.getUserRole()
        );

        String token = tokenServiceGateway.generateToken(context);


        return new AuthenticationToken(
                token,
                tokenServiceGateway.expiryTime()
        );


    }
}
