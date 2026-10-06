package com.granacerta.modules.financialConnection.infra.persistence.adapter;

import ai.pluggy.client.response.Account;
import ai.pluggy.client.response.AccountsResponse;
import com.granacerta.modules.financialAccount.domain.enums.FinancialAccountType;
import com.granacerta.modules.financialConnection.infra.persistence.external.pluggy.client.dto.ExternalFinancialAccount;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class PluggyFinancialAccountMapper {

    public List<ExternalFinancialAccount> toExternalFinancialAccounts(
            AccountsResponse response
    ) {
        return response.getResults()
                .stream()
                .map(this::toExternalFinancialAccount)
                .toList();
    }

    public ExternalFinancialAccount toExternalFinancialAccount(
            Account account
    ) {
        return new ExternalFinancialAccount(
                account.getId(),
                account.getName(),
                mapType(account.getType()),
                BigDecimal.valueOf(account.getBalance())
        );
    }

    private FinancialAccountType mapType(String type) {
        return switch (type) {
            case "BANK" -> FinancialAccountType.BANK;
            case "CREDIT" -> FinancialAccountType.CREDIT;
            default -> throw new IllegalArgumentException(
                    "Unknown Pluggy account type: " + type
            );
        };
    }
}