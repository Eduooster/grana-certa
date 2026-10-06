package com.granacerta.modules.transfer.domain.entity;

import com.granacerta.modules.transfer.application.usecase.CreateTransferCommand;
import com.granacerta.modules.transfer.application.usecase.UpdateTransferCommand;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class Transfer {
    private UUID id;
    private UUID userId;
    private UUID sourceAccountId;
    private UUID destinationAccountId;
    private BigDecimal amount;
    private LocalDateTime createdAt;
    private LocalDateTime transferredAt;
    private boolean active = true;

    public static Transfer create(CreateTransferCommand command) {
        Transfer transfer = new Transfer();

        transfer.userId = command.userId();
        transfer.sourceAccountId = command.sourceAccountId();
        transfer.destinationAccountId = command.destinationAccountId();
        transfer.amount = command.amount();
        transfer.createdAt = LocalDateTime.now();
        transfer.transferredAt = LocalDateTime.now();

        return transfer;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public UUID getSourceAccountId() {
        return sourceAccountId;
    }

    public void setSourceAccountId(UUID sourceAccountId) {
        this.sourceAccountId = sourceAccountId;
    }

    public UUID getDestinationAccountId() {
        return destinationAccountId;
    }

    public void setDestinationAccountId(UUID destinationAccountId) {
        this.destinationAccountId = destinationAccountId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getTransferredAt() {
        return transferredAt;
    }

    public void setTransferredAt(LocalDateTime transferredAt) {
        this.transferredAt = transferredAt;
    }

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

    public void update(UpdateTransferCommand command) {
        if (command.destinationAccountId() != null) {
            this.destinationAccountId = command.destinationAccountId();
        }

        if (command.amount() != null) {
            this.amount = command.amount();
        }
    }
}
