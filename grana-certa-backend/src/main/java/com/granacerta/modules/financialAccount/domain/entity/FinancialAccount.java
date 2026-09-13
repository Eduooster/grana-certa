package com.granacerta.modules.financialAccount.domain.entity;

import com.granacerta.modules.financialAccount.domain.enums.FinancialAccountSource;
import com.granacerta.modules.financialAccount.domain.enums.FinancialAccountStatus;
import com.granacerta.modules.financialAccount.domain.enums.FinancialAccountType;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class FinancialAccount {
    private UUID id;
    private UUID userId;
    private UUID connectionId;
    private String name;
    private FinancialAccountType
            type;
    private BigDecimal balance;
    private BigDecimal initialBalance;
    private FinancialAccountStatus status;
    private FinancialAccountSource source;
    private String externalId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public FinancialAccount(UUID id, UUID userId, UUID connectionId, String name, FinancialAccountType type, BigDecimal balance, BigDecimal initialBalance, FinancialAccountStatus status, FinancialAccountSource source, String externalId, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.userId = userId;
        this.connectionId = connectionId;
        this.name = name;
        this.type = type;
        this.balance = balance;
        this.initialBalance = initialBalance;
        this.status = status;
        this.source = source;
        this.externalId = externalId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static FinancialAccount createDefaultWallet(UUID userId) {
        return new FinancialAccount(
                null,
                userId,
                null,
                "Carteira",
                FinancialAccountType.WALLET,
                BigDecimal.ZERO,
                BigDecimal.ZERO,
                FinancialAccountStatus.ACTIVE,
                FinancialAccountSource.MANUAL,
                null,
                null,
                null
        );
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

    public UUID getConnectionId() {
        return connectionId;
    }

    public void setConnectionId(UUID connectionId) {
        this.connectionId = connectionId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public BigDecimal getInitialBalance() {
        return initialBalance;
    }

    public void setInitialBalance(BigDecimal initialBalance) {
        this.initialBalance = initialBalance;
    }

    public FinancialAccountStatus getStatus() {
        return status;
    }

    public void setStatus(FinancialAccountStatus status) {
        this.status = status;
    }

    public FinancialAccountType getType() {
        return type;
    }

    public void setType(FinancialAccountType type) {
        this.type = type;
    }

    public FinancialAccountSource getSource() {
        return source;
    }

    public void setSource(FinancialAccountSource source) {
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
        FinancialAccount that = (FinancialAccount) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}