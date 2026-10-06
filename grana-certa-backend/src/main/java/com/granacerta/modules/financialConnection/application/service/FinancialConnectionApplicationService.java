package com.granacerta.modules.financialConnection.application.service;

import ai.pluggy.client.response.ItemResponse;
import com.granacerta.modules.financialConnection.application.gateway.FinancialConnectionProviderGateway;
import com.granacerta.modules.financialConnection.domain.entity.FinancialConnection;
import com.granacerta.modules.financialConnection.domain.repository.FinancialConnectionRepository;
import com.granacerta.modules.financialConnection.infra.persistence.external.pluggy.client.dto.ExternalFinancialConnection;
import com.granacerta.modules.financialInstitution.domain.entity.FinancialInstitution;
import com.granacerta.modules.financialInstitution.domain.repository.FinancialInstitutionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class FinancialConnectionApplicationService {

    private final FinancialConnectionRepository financialConnectionRepository;
    private final FinancialInstitutionRepository financialInstitutionRepository;
    private final FinancialConnectionProviderGateway financialConnectionProviderGateway;

    @Transactional
    public FinancialConnection getOrCreate(
            String externalId,
            UUID userId,
            Long connectorId,
            String institutionName,
            String institutionImageUrl,
            String health,
            String type
    ) {
        return financialConnectionRepository
                .findByExternalId(externalId)
                .orElseGet(() -> create(
                        externalId,
                        userId,
                        connectorId,
                        institutionName,
                        institutionImageUrl,
                        health,
                        type
                ));
    }

    private FinancialConnection create(
            String externalId,
            UUID userId,
            Long connectorId,
            String institutionName,
            String institutionImageUrl,
            String health,
            String type
    ) {
        FinancialInstitution institution =
                resolveInstitution(
                        connectorId,
                        institutionName,
                        institutionImageUrl,
                        health,
                        type
                );

        FinancialConnection connection =
                FinancialConnection.create(
                        userId,
                        institution.getId(),
                        "PLUGGY",
                        externalId
                );

        return financialConnectionRepository.save(connection);
    }

    private FinancialInstitution resolveInstitution(
            Long connectorId,
            String name,
            String imageUrl,
            String health,
            String type
    ) {
        return financialInstitutionRepository
                .findByConnectorId(connectorId)
                .orElseGet(() -> {
                    FinancialInstitution institution =
                            FinancialInstitution.create(
                                    connectorId,
                                    name,
                                    imageUrl
                                    //health,
                                    //type
                            );

                    return financialInstitutionRepository.save(institution);
                });
    }}

