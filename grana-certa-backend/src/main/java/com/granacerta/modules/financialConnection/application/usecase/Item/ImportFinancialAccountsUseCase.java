package com.granacerta.modules.financialConnection.application.usecase.Item;


import com.granacerta.modules.financialAccount.domain.entity.FinancialAccount;
import com.granacerta.modules.financialAccount.domain.repository.FinancialAccountRepository;
import com.granacerta.modules.financialConnection.application.gateway.FinancialAccountProviderGateway;
import com.granacerta.modules.financialConnection.application.usecase.ImportFinancialAccountsCommand;
import com.granacerta.modules.financialConnection.infra.persistence.external.pluggy.client.dto.ExternalFinancialAccount;
import com.granacerta.modules.financialConnection.domain.entity.FinancialConnection;
import com.granacerta.modules.financialConnection.domain.exception.FinancialConnectionNotFoundException;
import com.granacerta.modules.financialConnection.domain.repository.FinancialConnectionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
public class ImportFinancialAccountsUseCase {

    private final FinancialConnectionRepository financialConnectionRepository;
    private final FinancialAccountRepository financialAccountRepository;
    private final FinancialAccountProviderGateway financialAccountProviderGateway;

    public ImportFinancialAccountsUseCase(FinancialConnectionRepository financialConnectionRepository, FinancialAccountRepository financialAccountRepository, FinancialAccountProviderGateway financialAccountProviderGateway) {
        this.financialConnectionRepository = financialConnectionRepository;
        this.financialAccountRepository = financialAccountRepository;
        this.financialAccountProviderGateway = financialAccountProviderGateway;
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

        for (ExternalFinancialAccount externalAccount : externalAccounts) {


            if (financialAccountRepository
                    .findByExternalId(externalAccount.externalId())
                    .isPresent()) {


                continue;
            }

            FinancialAccount account =
                    FinancialAccount.createFromConnection(
                            connection,
                            externalAccount.name(),
                            externalAccount.type(),
                            externalAccount.balance(),
                            externalAccount.externalId()
                    );

            financialAccountRepository.save(account);

        }

    }
}
