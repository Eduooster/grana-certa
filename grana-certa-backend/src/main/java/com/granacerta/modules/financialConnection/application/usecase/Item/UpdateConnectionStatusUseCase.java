package com.granacerta.modules.financialConnection.application.usecase.Item;

import com.granacerta.modules.financialConnection.domain.entity.FinancialConnection;
import com.granacerta.modules.financialConnection.domain.repository.FinancialConnectionRepository;
import org.springframework.transaction.annotation.Transactional;

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

        connection.updateStatus(command.status());

        financialConnectionRepository.save(connection);
    }
}