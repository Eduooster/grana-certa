package com.granacerta.modules.transaction.domain.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

public class Transaction {
    private Long id;
    private Long userId;
    private Long accountId;
    private Long categoryId;
    private Long invoiceId;
    private Long transferId;
    private Long recurrenceId;
    private String type;
    private BigDecimal amount;
    private LocalDate date;
    private String description;
    private String source;
    private String externalId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
