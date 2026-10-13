package com.granacerta.modules.financialConnection.application.usecase.SyncConnection;

import com.granacerta.modules.financialConnection.domain.entity.FinancialConnection;
import com.granacerta.modules.financialConnection.domain.enums.FinancialConnectionStatus;
import com.granacerta.modules.financialConnection.domain.repository.FinancialConnectionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
public class UpdateConnectionStatusUseCase {

    private final FinancialConnectionRepository financialConnectionRepository;

    public UpdateConnectionStatusUseCase(
            FinancialConnectionRepository financialConnectionRepository
    ) {
        this.financialConnectionRepository = financialConnectionRepository;
    }

    @Transactional
    public void execute(UpdateConnectionStatusCommand command) {

        FinancialConnection connection =
                financialConnectionRepository
                        .findByExternalId(command.itemId())
                        .orElseThrow();

        log.info("before update connection status: " + connection.getStatus());
        connection.updateStatus(command.status());

        log.info("after update connection status: " + connection.getStatus());

        financialConnectionRepository.save(connection);
    }


}