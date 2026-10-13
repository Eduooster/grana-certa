package com.granacerta.modules.financialConnection.application.usecase.Process;

import com.granacerta.modules.financialConnection.application.gateway.FinancialConnectionProviderGateway;
import com.granacerta.modules.financialConnection.application.service.FinancialConnectionApplicationService;
import com.granacerta.modules.financialConnection.domain.entity.FinancialConnection;
import com.granacerta.modules.financialConnection.infra.persistence.external.pluggy.client.dto.ExternalFinancialConnection;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Slf4j
public class ProcessFinancialConnectionCreatedUseCase {

    private final FinancialConnectionApplicationService financialConnectionService;
    private final FinancialConnectionProviderGateway financialConnectionProviderGateway;





    public ProcessFinancialConnectionCreatedUseCase(FinancialConnectionApplicationService financialConnectionService, FinancialConnectionProviderGateway financialConnectionProviderGateway) {
        this.financialConnectionService = financialConnectionService;
        this.financialConnectionProviderGateway = financialConnectionProviderGateway;



    }

    @Transactional
    public void execute(String externalId) {

            ExternalFinancialConnection externalConnection =
                    financialConnectionProviderGateway.getItemDetails(externalId);

            UUID userId = UUID.fromString(
                    externalConnection.userId()
            );



            //ver se mantem esse mapper aqui, ou mapear no gateway




         FinancialConnection connection= financialConnectionService.getOrCreate(
                    externalConnection.externalId(),
                    userId,
                    externalConnection.connectorId(),
                    externalConnection.institutionName(),
                    externalConnection.institutionImageUrl(),
                    externalConnection.health(),
                    externalConnection.type(),
                   externalConnection.connectionExecutionResult()


            );

            connection.updateLastSync();





    }
}
