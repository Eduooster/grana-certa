package com.granacerta.modules.transfer.domain.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

public class Transfer {
    private Long id;
    private Long userId;
    private Long sourceAccountId;
    private Long destinationAccountId;
    private BigDecimal amount;
    private LocalDateTime createdAt;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transfer transfer = (Transfer) o;
        return Objects.equals(id, transfer.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
