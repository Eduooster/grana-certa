package com.granacerta.modules.financialConnection.infra.persistence.external.pluggy.webhook.handler;

import ai.pluggy.client.response.ItemStatus;
import com.granacerta.modules.financialConnection.application.usecase.SyncConnection.SynchronizeFinancialConnectionUseCase;
import com.granacerta.modules.financialConnection.application.usecase.SyncConnection.SynchronizePartiallyUpdatedProducts;
import com.granacerta.modules.financialConnection.domain.enums.FinancialConnectionStatus;
import com.granacerta.modules.financialConnection.infra.persistence.external.pluggy.client.dto.ExternalFinancialConnection;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class UpdatedItemStatusHandler implements ItemStatusHandler {

    private final SynchronizeFinancialConnectionUseCase synchronizeFinancialConnectionUseCase;
    private final SynchronizePartiallyUpdatedProducts synchronizePartiallyUpdatedProducts;


    public UpdatedItemStatusHandler(SynchronizeFinancialConnectionUseCase synchronizeFinancialConnectionUseCase, SynchronizePartiallyUpdatedProducts synchronizePartiallyUpdatedProducts) {
        this.synchronizeFinancialConnectionUseCase = synchronizeFinancialConnectionUseCase;
        this.synchronizePartiallyUpdatedProducts = synchronizePartiallyUpdatedProducts;
    }

    @Override
    public boolean supports(FinancialConnectionStatus status) {
        return status == FinancialConnectionStatus.CONNECTED;
    }

    @Override
    public void handle(ExternalFinancialConnection connection) {
        switch (connection.connectionExecutionResult()) {
            case SUCCESS -> handleSuccess(connection);
            case PARTIAL_SUCCESS -> handlePartialSuccess(connection);
            default -> log.warn(
                    "Execution status não tratado: {}",
                    connection.connectionExecutionResult()
            );
        }
    }

    private void handleSuccess(ExternalFinancialConnection connection) {
        log.info("Successfully updated item status");
        synchronizeFinancialConnectionUseCase.execute(connection);
    }


    private void handlePartialSuccess(ExternalFinancialConnection connection) {
        log.info("Successfully partially updated item status");
        synchronizePartiallyUpdatedProducts.execute(connection);

    }
}