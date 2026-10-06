package com.granacerta.modules.financialConnection.application.usecase.Item;

import com.granacerta.modules.financialConnection.application.gateway.FinancialConnectionProviderGateway;
import com.granacerta.modules.financialConnection.application.service.FinancialConnectionApplicationService;
import com.granacerta.modules.financialConnection.infra.persistence.external.pluggy.client.dto.ExternalFinancialConnection;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;



@Slf4j
public class ProcessFinancialConnectionLoginSuccessUseCase {


    private final FinancialConnectionProviderGateway financialConnectionProviderGateway;
    private final FinancialConnectionApplicationService financialConnectionService;
    private final ImportFinancialAccountsUseCase importFinancialAccountsUseCase;

        public ProcessFinancialConnectionLoginSuccessUseCase(
                FinancialConnectionProviderGateway financialConnectionProviderGateway, FinancialConnectionApplicationService financialConnectionService, ImportFinancialAccountsUseCase importFinancialAccountsUseCase) {

            this.financialConnectionProviderGateway = financialConnectionProviderGateway;
            this.financialConnectionService = financialConnectionService;
            this.importFinancialAccountsUseCase = importFinancialAccountsUseCase;
        }

    @Transactional
    public void execute(String externalId) {

        ExternalFinancialConnection externalConnection =
                financialConnectionProviderGateway.getItemDetails(externalId);



        UUID userId = UUID.fromString(
                externalConnection.userId()
        );

        financialConnectionService.getOrCreate(
                externalConnection.externalId(),
                userId,
                externalConnection.connectorId(),
                externalConnection.institutionName(),
                externalConnection.institutionImageUrl(),
                externalConnection.health(),
                externalConnection.type()
        );


    }
    }
