package com.granacerta.modules.financialConnection.domain.entity;

import com.granacerta.modules.financialConnection.domain.enums.ConnectionExecutionResult;

import com.granacerta.modules.financialConnection.domain.enums.FinancialConnectionStatus;

import com.granacerta.modules.user.domain.entity.User;

import java.time.LocalDateTime;
import java.util.UUID;

public class FinancialConnection {

    private UUID id;
    private UUID userId;
    private UUID financialInstitutionId;

    private String provider;
    private String externalId;

    private FinancialConnectionStatus status;
    private ConnectionExecutionResult executionStatus;


    private LocalDateTime connectedAt;
    private LocalDateTime lastSyncedAt;
    private Boolean isDeleted  = Boolean.FALSE;


    public FinancialConnection() {
    }



    public static FinancialConnection create(UUID userId, UUID financialInstitutionId, String provider, String externalId ,ConnectionExecutionResult executionResult,FinancialConnectionStatus financialConnectionStatus) {
        FinancialConnection connection = new FinancialConnection();
        connection.userId = userId;
        connection.financialInstitutionId = financialInstitutionId;
        connection.provider = provider;
        connection.externalId = externalId;
        connection.status = FinancialConnectionStatus.CONNECTED;
        connection.executionStatus =executionResult;

        connection.connectedAt = LocalDateTime.now();
        connection.lastSyncedAt = LocalDateTime.now();

        connection.status = financialConnectionStatus;

        return connection;
    }

    public ConnectionExecutionResult getExecutionStatus() {
        return executionStatus;
    }

    public void setExecutionStatus(ConnectionExecutionResult executionStatus) {
        this.executionStatus = executionStatus;
    }

    public UUID getId() {
        return id;
    }


    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
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

    public LocalDateTime getConnectedAt() {
        return connectedAt;
    }

    public void setConnectedAt(LocalDateTime connectedAt) {
        this.connectedAt = connectedAt;
    }

    public LocalDateTime getLastSyncedAt() {
        return lastSyncedAt;
    }

    public void setLastSyncedAt(LocalDateTime lastSyncedAt) {
        this.lastSyncedAt = lastSyncedAt;
    }

    public UUID getFinancialInstitutionId() {
        return financialInstitutionId;
    }

    public void setFinancialInstitutionId(UUID financialInstitutionId) {
        this.financialInstitutionId = financialInstitutionId;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public FinancialConnectionStatus getStatus() {
        return status;
    }

    public void setStatus(FinancialConnectionStatus status) {
        this.status = status;
    }


    public void updateStatus(FinancialConnectionStatus status) {
        this.status = status;
    }

    public void updateLastSync() {
        lastSyncedAt = LocalDateTime.now();
    }





}
