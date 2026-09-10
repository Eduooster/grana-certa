package com.granacerta.modules.financialAccount.domain.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

public class FinancialAccount {
    private Long id;
    private Long userId;
    private Long connectionId;
    private String name;
    private String type;
    private BigDecimal balance;
    private BigDecimal initialBalance;
    private String status;
    private String source;
    private String externalId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FinancialAccount that = (FinancialAccount) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}