package com.granacerta.modules.transaction.domain.entity;

import com.granacerta.modules.transaction.application.usecase.CreateTransactionCommand;
import com.granacerta.modules.transaction.application.usecase.UpdateTransactionCommand;
import com.granacerta.modules.transaction.domain.enums.TransactionSource;
import com.granacerta.modules.transaction.domain.enums.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class Transaction {
    private UUID id;
    private UUID userId;
    private UUID accountId;
    private UUID categoryId;
    private UUID invoiceId;
    private UUID transferId;
    private UUID recurrenceId;
    private TransactionType type;
    private BigDecimal amount;
    private LocalDate transactionDate;
    private String description;
    private TransactionSource source;
    private String externalId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private boolean active = true;

    public static Transaction createManualTransaction(CreateTransactionCommand command) {
        Transaction transaction = new Transaction();

        transaction.userId = command.userId();
        transaction.accountId = command.accountId();
        transaction.categoryId = command.categoryId();
        transaction.type = command.type();
        transaction.amount = command.amount();
        transaction.transactionDate = command.transactionDate();
        transaction.description = command.description();

        transaction.source = TransactionSource.MANUAL;
        transaction.externalId = null;

        transaction.createdAt = LocalDateTime.now();
        transaction.updatedAt = null;

        return transaction;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
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

    public UUID getAccountId() {
        return accountId;
    }

    public void setAccountId(UUID accountId) {
        this.accountId = accountId;
    }

    public UUID getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(UUID categoryId) {
        this.categoryId = categoryId;
    }

    public UUID getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(UUID invoiceId) {
        this.invoiceId = invoiceId;
    }

    public UUID getTransferId() {
        return transferId;
    }

    public void setTransferId(UUID transferId) {
        this.transferId = transferId;
    }

    public UUID getRecurrenceId() {
        return recurrenceId;
    }

    public void setRecurrenceId(UUID recurrenceId) {
        this.recurrenceId = recurrenceId;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDate getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDate transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TransactionSource getSource() {
        return source;
    }

    public void setSource(TransactionSource source) {
        this.source = source;
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

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

    public void update(UpdateTransactionCommand command) {

    }

    public void deactivate() {
        this.active = false;
        this.updatedAt = LocalDateTime.now();
    }
}
