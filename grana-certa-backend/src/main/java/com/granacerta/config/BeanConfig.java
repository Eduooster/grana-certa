package com.granacerta.config;

import com.granacerta.modules.authentication.application.gateway.PasswordGateway;
import com.granacerta.modules.authentication.application.gateway.TokenServiceGateway;
import com.granacerta.modules.authentication.application.usecase.LoginUseCase;
import com.granacerta.modules.authentication.application.usecase.RegisterUserLocalUseCase;
import com.granacerta.modules.authentication.domain.repository.UserCredentialRepository;
import com.granacerta.modules.category.application.usecase.CreateCategoryUseCase;
import com.granacerta.modules.category.domain.repository.CategoryRepository;
import com.granacerta.modules.financialAccount.application.usecase.CreateFinancialAccountUseCase;
import com.granacerta.modules.financialAccount.domain.repository.FinancialAccountRepository;
import com.granacerta.modules.financialConnection.application.gateway.FinancialAccountProviderGateway;
import com.granacerta.modules.financialConnection.application.gateway.FinancialConnectionProviderGateway;
import com.granacerta.modules.financialConnection.application.gateway.FinancialTransactionProviderGateway;
import com.granacerta.modules.financialConnection.application.service.FinancialConnectionApplicationService;
import com.granacerta.modules.financialConnection.application.usecase.CreateFinancialConnectionUseCase;
import com.granacerta.modules.financialConnection.application.usecase.GenerateConnectionTokenUseCase;
import com.granacerta.modules.financialConnection.application.usecase.Item.*;

import com.granacerta.modules.financialConnection.domain.repository.FinancialConnectionRepository;

import com.granacerta.modules.financialInstitution.application.usecase.CreateFinancialInstitutionUseCase;
import com.granacerta.modules.financialInstitution.domain.repository.FinancialInstitutionRepository;
import com.granacerta.modules.financialProfile.application.usecase.CreateFinancialProfileUseCase;
import com.granacerta.modules.financialProfile.application.usecase.DeleteFinancialProfileUseCase;
import com.granacerta.modules.financialProfile.application.usecase.GetFinancialProfileUseCase;
import com.granacerta.modules.financialProfile.application.usecase.UpdateFinancialProfileUseCase;
import com.granacerta.modules.financialProfile.domain.repository.FinancialProfileRepository;
import com.granacerta.modules.invoice.application.usecase.CreateInvoiceUseCase;
import com.granacerta.modules.invoice.domain.repository.InvoiceRepository;
import com.granacerta.modules.invoice.domain.resolve.InvoiceResolver;
import com.granacerta.modules.recurrence.application.usecase.CreateRecurrenceUseCase;
import com.granacerta.modules.recurrence.domain.repository.RecurrenceRepository;
import com.granacerta.modules.transaction.application.usecase.*;
import com.granacerta.modules.transaction.domain.repository.TransactionRepository;
import com.granacerta.modules.transaction.orchestrator.CreateTransactionOrchestrator;
import com.granacerta.modules.transfer.application.usecase.CreateTransferUseCase;
import com.granacerta.modules.transfer.application.usecase.UpdateTransferUseCase;
import com.granacerta.modules.transfer.domain.repository.TransferRepository;
import com.granacerta.modules.user.domain.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
        public CreateFinancialInstitutionUseCase createFinancialInstitutionUseCase(
                FinancialInstitutionRepository financialInstitutionRepository
        ) {
            return new CreateFinancialInstitutionUseCase(financialInstitutionRepository);
        }


        @Bean
        public CreateFinancialConnectionUseCase createFinancialConnectionUseCase(
                FinancialConnectionRepository financialConnectionRepository,
                FinancialInstitutionRepository financialInstitutionRepository,
                FinancialConnectionProviderGateway
                 financialConnectionProviderGateway
        ) {
            return new CreateFinancialConnectionUseCase(
                    financialConnectionRepository,
                    financialInstitutionRepository,financialConnectionProviderGateway
            );
        }
        @Bean
        public CreateTransactionUseCase createTransactionUseCase(FinancialAccountRepository financialAccountRepository, CategoryRepository categoryRepository, TransactionRepository transactionRepository,InvoiceResolver invoiceResolver) {
        return new CreateTransactionUseCase(financialAccountRepository,categoryRepository,transactionRepository,invoiceResolver  );
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
        @Bean
        public CreateTransferUseCase createTranferUseCase (TransferRepository transferRepository,
                                                           FinancialAccountRepository financialAccountRepository, TransactionRepository transactionRepository) {
        return new CreateTransferUseCase(
                transferRepository,financialAccountRepository,transactionRepository
        );

        }

        @Bean
    
        public CreateFinancialAccountUseCase createFinancialAccountUseCase (FinancialAccountRepository financialAccountRepository) {
        return new CreateFinancialAccountUseCase(financialAccountRepository);
        }

        @Bean
        public UpdateTransferUseCase updateTransferUseCase (TransferRepository transferRepository) {
        return new UpdateTransferUseCase(transferRepository);
        }

        @Bean
        public CreateRecurrenceUseCase createRecurrenceUseCase (FinancialAccountRepository financialAccountRepository,CategoryRepository categoryRepository,RecurrenceRepository recurrenceRepository  ) {
        return new CreateRecurrenceUseCase(financialAccountRepository,categoryRepository,recurrenceRepository);
        }


    @Bean
    public CreateTransactionOrchestrator createTransactionOrchestrator(
            CreateTransactionUseCase createTransactionUseCase,
            CreateRecurrenceUseCase createRecurrenceUseCase
    ) {
        return new CreateTransactionOrchestrator(
                createTransactionUseCase,
                createRecurrenceUseCase
        );
    }

    @Bean
    
public InvoiceResolver invoiceResolver(InvoiceRepository invoiceRepository) {
        return new InvoiceResolver(invoiceRepository);
    }
    @Bean
    public CreateInvoiceUseCase createInvoiceUseCase ( FinancialAccountRepository financialAccountRepository ,InvoiceRepository invoiceRepository) {

        return new CreateInvoiceUseCase(financialAccountRepository,invoiceRepository    );
    }


    @Bean
    public ImportFinancialAccountsUseCase importFinancialAccountsUseCase (
            FinancialConnectionRepository financialConnectionRepository,
            FinancialAccountRepository financialAccountRepository   ,

            FinancialAccountProviderGateway financialAccountProviderGateway


    ){
        return new ImportFinancialAccountsUseCase(
                financialConnectionRepository,financialAccountRepository,financialAccountProviderGateway
        );
    }



    @Bean
    public GenerateConnectionTokenUseCase generateConnectionTokenUseCase (FinancialConnectionProviderGateway financialConnectionProviderGateway) {
        return new GenerateConnectionTokenUseCase(financialConnectionProviderGateway);
    }

    @Bean
    public ProcessFinancialConnectionLoginSuccessUseCase handlePluggyLoginSucceededUseCase(FinancialConnectionProviderGateway financialConnectionProviderGateway, FinancialConnectionApplicationService financialConnectionApplicationService, ImportFinancialAccountsUseCase importFinancialAccountsUseCase) {
        return new ProcessFinancialConnectionLoginSuccessUseCase(financialConnectionProviderGateway, financialConnectionApplicationService, importFinancialAccountsUseCase);
    }

    @Bean
    public ProcessFinancialConnectionCreatedUseCase handleItemCreatedUseCase (FinancialConnectionApplicationService financialConnectionApplicationService, FinancialConnectionProviderGateway financialConnectionProviderGateway
    , SyncFinancialConnectionUseCase syncFinancialConnectionUseCase){

        return new ProcessFinancialConnectionCreatedUseCase(financialConnectionApplicationService,financialConnectionProviderGateway,syncFinancialConnectionUseCase);

    }

    @Bean
    public ImportTransactionsUseCase importTransactionsUseCase (FinancialConnectionRepository financialConnectionRepository,
                                                                FinancialTransactionProviderGateway financialTransactionProviderGateway,
                                                                FinancialAccountRepository financialAccountRepository,
                                                                TransactionRepository transactionRepository) {
        return new ImportTransactionsUseCase(financialConnectionRepository,financialTransactionProviderGateway,financialAccountRepository,transactionRepository);
    }

    @Bean
    public SyncFinancialConnectionUseCase syncFinancialConnectionUseCase (
            FinancialConnectionRepository financialConnectionRepository,ImportFinancialAccountsUseCase importFinancialAccountsUseCase,ImportTransactionsUseCase importTransactionsUseCase
    ){
        return new SyncFinancialConnectionUseCase(financialConnectionRepository,importFinancialAccountsUseCase,importTransactionsUseCase);
    }

    @Bean
    public ProcessTransactionCreatedUseCase processTransactionCreatedUseCase (
            FinancialConnectionRepository financialConnectionRepository,
            FinancialTransactionProviderGateway financialTransactionProviderGateway,
            TransactionRepository transactionRepository,
            FinancialAccountRepository financialAccountRepository
    ){
        return new ProcessTransactionCreatedUseCase(
                financialConnectionRepository,financialTransactionProviderGateway,transactionRepository,financialAccountRepository
        );
    }

    @Bean
    public UpdateConnectionStatusUseCase updateConnectionStatusUseCase (
            FinancialConnectionRepository financialConnectionRepository
    ){
        return new UpdateConnectionStatusUseCase(financialConnectionRepository);
    }


}
