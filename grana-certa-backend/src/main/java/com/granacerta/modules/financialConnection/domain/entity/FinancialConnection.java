package com.granacerta.modules.financialConnection.domain.entity;

import com.granacerta.modules.financialConnection.domain.enums.FinancialConnectionStatus;
import com.granacerta.modules.financialConnection.domain.enums.SyncStatus;
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
    private  SyncStatus syncStatus;

    private LocalDateTime connectedAt;
    private LocalDateTime lastSyncedAt;

    public FinancialConnection() {
    }

    public FinancialConnection(String externalId, User user) {


    }


    public static FinancialConnection create(UUID userId, UUID financialInstitutionId, String provider, String externalId ) {
        FinancialConnection connection = new FinancialConnection();
        connection.userId = userId;
        connection.financialInstitutionId = financialInstitutionId;
        connection.provider = provider;
        connection.externalId = externalId;
        connection.status = FinancialConnectionStatus.ACTIVE;
        connection.syncStatus = SyncStatus.PENDING;
        connection.connectedAt = LocalDateTime.now();
        return connection;
    }

    public SyncStatus getSyncStatus() {
        return syncStatus;
    }

    public UUID getId() {
        return id;
    }

    public void setSyncStatus(SyncStatus syncStatus) {
        this.syncStatus = syncStatus;
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

    public void startSync() {
        this.syncStatus = SyncStatus.SYNCING;
    }

    public void completeSync() {
        this.syncStatus = SyncStatus.COMPLETED;
    }

    public void failSync() {
        this.syncStatus = SyncStatus.ERROR;

    }

    public void updateStatus(FinancialConnectionStatus status) {
        this.status = status;
    }
}
