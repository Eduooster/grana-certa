package com.granacerta.config;

import com.granacerta.modules.authentication.application.gateway.PasswordGateway;
import com.granacerta.modules.authentication.application.gateway.TokenServiceGateway;
import com.granacerta.modules.authentication.application.usecase.LoginUseCase;
import com.granacerta.modules.authentication.application.usecase.RegisterUserLocalUseCase;
import com.granacerta.modules.authentication.domain.repository.UserCredentialRepository;
import com.granacerta.modules.category.application.usecase.CreateCategoryUseCase;
import com.granacerta.modules.category.domain.repository.CategoryRepository;
import com.granacerta.modules.financialAccount.domain.repository.FinancialAccountRepository;
import com.granacerta.modules.financialProfile.application.usecase.CreateFinancialProfileUseCase;
import com.granacerta.modules.financialProfile.application.usecase.DeleteFinancialProfileUseCase;
import com.granacerta.modules.financialProfile.application.usecase.GetFinancialProfileUseCase;
import com.granacerta.modules.financialProfile.application.usecase.UpdateFinancialProfileUseCase;
import com.granacerta.modules.financialProfile.domain.repository.FinancialProfileRepository;
import com.granacerta.modules.transaction.application.usecase.*;
import com.granacerta.modules.transaction.domain.repository.TransactionRepository;
import com.granacerta.modules.user.domain.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.print.DocFlavor;

@Configuration
public class BeanConfig {

    @Bean
    public LoginUseCase loginUse (PasswordGateway passwordGateway, TokenServiceGateway tokenServiceGateway, UserCredentialRepository userCredentialRepository , UserRepository userRepository) {
        return new LoginUseCase(passwordGateway,tokenServiceGateway,userCredentialRepository,userRepository);
    }

    @Bean
    public RegisterUserLocalUseCase registerUserUseCase(UserRepository userRepository,
                                                        PasswordGateway passwordGateway,
                                                        UserCredentialRepository userCredentialRepository    ,   TokenServiceGateway tokenServiceGateway,
                                                        FinancialAccountRepository financialAccountRepository
                                                      ) {
        return new RegisterUserLocalUseCase(
                userRepository,passwordGateway,userCredentialRepository ,tokenServiceGateway,financialAccountRepository

        );}


        @Bean
        public CreateFinancialProfileUseCase createFinancialProfileUseCase(FinancialProfileRepository financialProfileRepository,UserRepository userRepository) {
        return new CreateFinancialProfileUseCase(financialProfileRepository,userRepository  );
        }
        @Bean
        public GetFinancialProfileUseCase getFinancialProfileUseCase(FinancialProfileRepository financialProfileRepository) {
            return new GetFinancialProfileUseCase(financialProfileRepository);
        }

        @Bean
        public UpdateFinancialProfileUseCase updateFinancialProfileUseCase(FinancialProfileRepository financialProfileRepository) {
            return new UpdateFinancialProfileUseCase(financialProfileRepository);
        }

        @Bean
        public DeleteFinancialProfileUseCase deleteFinancialProfileUseCase(FinancialProfileRepository financialProfileRepository) {
            return new DeleteFinancialProfileUseCase(financialProfileRepository);
        }

        @Bean
        public CreateCategoryUseCase createCategoryUseCase(CategoryRepository categoryRepository) {
        return new CreateCategoryUseCase(categoryRepository);
        }
        @Bean
        public CreateTransactionUseCase createTransactionUseCase(TransactionRepository transactionRepository,CategoryRepository categoryRepository,FinancialAccountRepository financialAccountRepository ) {
        return new CreateTransactionUseCase(transactionRepository,categoryRepository, financialAccountRepository     );
        }
        @Bean
        public GetTransactionsUseCase getTransactionsUseCas (TransactionRepository transactionRepository) {
        return new GetTransactionsUseCase(transactionRepository);
        }


        @Bean
        public GetTransactionUseCase getTransactionUseCas (TransactionRepository transactionRepository){
        return new GetTransactionUseCase(transactionRepository);
        }

        @Bean
        public UpdateTransactionUseCase updateTransactionUseCase (TransactionRepository transactionRepository,CategoryRepository categoryRepository) {
        return new UpdateTransactionUseCase(transactionRepository,categoryRepository);
        }
        @Bean
        public DeleteTransactionUseCase deleteTransactionUseCase (TransactionRepository transactionRepository) {
            return new DeleteTransactionUseCase(transactionRepository);
        }



}
