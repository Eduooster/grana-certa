package com.granacerta.modules.authentication.domain.entity;

import java.time.LocalDateTime;
import java.util.UUID;

public class UserCredential {
    private UUID id;
    private UUID userId;

    private String passwordHash;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String providerId;
    private AuthProvider provider;

    public UserCredential() {
    }



    public static UserCredential createLocal(String hash, UUID userId) {

        UserCredential credential = new UserCredential();
        credential.setPasswordHash(hash);
        credential.setUserId(userId);
        credential.setCreatedAt(LocalDateTime.now());
        credential.setUpdatedAt(LocalDateTime.now());
        credential.setProviderId(null);
        credential.setProvider(AuthProvider.LOCAL);

        return credential;
    }

    @Override
    public String toString() {
        return "UserCredential{" +
                "id=" + id +
                ", userId=" + userId +
                ", passwordHash='" + passwordHash + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", providerId='" + providerId + '\'' +
                ", provider=" + provider +
                '}';
    }

    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }

    public AuthProvider getProvider() {
        return provider;
    }


    public void setProvider(AuthProvider provider) {
        this.provider = provider;
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

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
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
}
