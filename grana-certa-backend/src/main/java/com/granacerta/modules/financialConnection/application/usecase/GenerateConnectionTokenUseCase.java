package com.granacerta.modules.financialConnection.application.usecase;

import com.granacerta.modules.financialConnection.application.gateway.FinancialConnectionProviderGateway;

public class GenerateConnectionTokenUseCase {

    private final FinancialConnectionProviderGateway financialConnectionProviderGateway;

    public GenerateConnectionTokenUseCase(FinancialConnectionProviderGateway financialConnectionProviderGateway) {
        this.financialConnectionProviderGateway = financialConnectionProviderGateway;
    }

    public String execute(GenerateConnectionTokenCommand command) {

        return financialConnectionProviderGateway.generateConnectionToken(
                command.userId()
        );
    }
}