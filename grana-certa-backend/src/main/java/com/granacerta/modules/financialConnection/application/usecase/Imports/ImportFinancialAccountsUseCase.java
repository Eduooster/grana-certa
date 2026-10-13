package com.granacerta.modules.financialConnection.application.usecase.Imports;


import ai.pluggy.client.response.Account;
import com.granacerta.modules.financialAccount.domain.entity.FinancialAccount;
import com.granacerta.modules.financialAccount.domain.enums.FinancialAccountType;
import com.granacerta.modules.financialAccount.domain.repository.FinancialAccountRepository;
import com.granacerta.modules.financialConnection.application.gateway.FinancialAccountProviderGateway;
import com.granacerta.modules.financialConnection.infra.persistence.external.pluggy.client.dto.ExternalFinancialAccount;
import com.granacerta.modules.financialConnection.domain.entity.FinancialConnection;
import com.granacerta.modules.financialConnection.domain.exception.FinancialConnectionNotFoundException;
import com.granacerta.modules.financialConnection.domain.repository.FinancialConnectionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class ImportFinancialAccountsUseCase {

    private final FinancialConnectionRepository financialConnectionRepository;
    private final FinancialAccountRepository financialAccountRepository;
    private final FinancialAccountProviderGateway financialAccountProviderGateway;
    private final ImportCreditCardInvoicesUseCase importCreditCardInvoicesUseCase;

    public ImportFinancialAccountsUseCase(FinancialConnectionRepository financialConnectionRepository, FinancialAccountRepository financialAccountRepository, FinancialAccountProviderGateway financialAccountProviderGateway, ImportCreditCardInvoicesUseCase importCreditCardInvoicesUseCase) {
        this.financialConnectionRepository = financialConnectionRepository;
        this.financialAccountRepository = financialAccountRepository;
        this.financialAccountProviderGateway = financialAccountProviderGateway;
        this.importCreditCardInvoicesUseCase = importCreditCardInvoicesUseCase;
    }

    @Transactional
    public void execute(String externalId) {


        FinancialConnection connection =
                financialConnectionRepository
                        .findByExternalId(externalId)
                        .orElseThrow(() ->


                            new FinancialConnectionNotFoundException(
                                    "Financial Connection with external id "
                                            + externalId
                                            + " not found"
                        ));

        List<ExternalFinancialAccount> externalAccounts =
                financialAccountProviderGateway.fetchAccounts(externalId);

        List<String> creditAccountExternalIds = new ArrayList<>();

        for (ExternalFinancialAccount externalAccount : externalAccounts) {


            if (financialAccountRepository
                    .findByExternalId(externalAccount.externalId())
                    .isPresent()) {


                continue;
            }

            if (externalAccount.type() == FinancialAccountType.CREDIT) {
                FinancialAccount account = FinancialAccount.createCreditCardFromConnection(
                        connection,
                        externalAccount.name(),
                        externalAccount.balance(),
                        externalAccount.externalId(),
                        externalAccount.creditLimit(),
                        externalAccount.availableCreditLimit(),
                        externalAccount.closeDate(),
                        externalAccount.dueDate()
                );
                creditAccountExternalIds.add(externalAccount.externalId());
                financialAccountRepository.save(account);
            } else {
                FinancialAccount account = FinancialAccount.createFromConnection(
                        connection,
                        externalAccount.name(),
                        externalAccount.type(),
                        externalAccount.balance(),
                        externalAccount.externalId()
                );
                financialAccountRepository.save(account);

            }



        }


        connection.updateLastSync();

        importCreditCardInvoicesUseCase.execute(externalId);
    }




}
