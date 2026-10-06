package com.granacerta.modules.financialConnection.application.usecase;

import ai.pluggy.client.response.ItemResponse;
import com.granacerta.modules.financialConnection.application.gateway.FinancialConnectionProviderGateway;
import com.granacerta.modules.financialConnection.domain.entity.FinancialConnection;
import com.granacerta.modules.financialConnection.infra.persistence.external.pluggy.client.dto.ExternalFinancialConnection;
import com.granacerta.modules.financialInstitution.domain.entity.FinancialInstitution;
import com.granacerta.modules.financialInstitution.domain.expception.FinancialInstitutionNotFoundException;
import com.granacerta.modules.financialConnection.domain.repository.FinancialConnectionRepository;
import com.granacerta.modules.financialInstitution.domain.repository.FinancialInstitutionRepository;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

@Slf4j
public class CreateFinancialConnectionUseCase {
    private final FinancialConnectionRepository financialConnectionRepository;
    private final FinancialInstitutionRepository financialInstitutionRepository;
    private final FinancialConnectionProviderGateway financialConnectionProviderGateway;

    public CreateFinancialConnectionUseCase(
            FinancialConnectionRepository financialConnectionRepository,
            FinancialInstitutionRepository financialInstitutionRepository, FinancialConnectionProviderGateway financialConnectionProviderGateway
    ) {
        this.financialConnectionRepository = financialConnectionRepository;
        this.financialInstitutionRepository = financialInstitutionRepository;
        this.financialConnectionProviderGateway = financialConnectionProviderGateway;
    }

    public UUID execute(CreateFinancialConnectionCommand command) {

        ExternalFinancialConnection itemResponse =
                financialConnectionProviderGateway.getItemDetails(command.itemId());





        FinancialInstitution financialInstitution = resolve(
                itemResponse.connectorId(),
                itemResponse.institutionName(),
                itemResponse.institutionImageUrl(),
                "teste",
                "teste"
        );

        FinancialConnection connection = FinancialConnection.create(
                command.userId(),
                financialInstitution.getId(),
               "teste",
                command.itemId()
        );

        return financialConnectionRepository.save(connection).getId();
    }

    public FinancialInstitution resolve(
            Long connectorId,
            String name,
            String imageUrl,
            String health,
            String bankType
    ) {
        return financialInstitutionRepository
                .findByConnectorId(connectorId)
                .orElseGet(() -> {
                    FinancialInstitution institution = FinancialInstitution.create(
                            connectorId,
                            name,
                            imageUrl
                            //health,
                            //bankType
                    );

                    return financialInstitutionRepository.save(institution);
                });
    }
}