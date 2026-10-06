package com.granacerta.modules.financialInstitution.domain.entity;

import com.granacerta.modules.financialInstitution.application.usecase.CreateFinancialInstitutionCommand;
import com.granacerta.modules.financialInstitution.domain.enums.FinancialInstitutionHealth;
import com.granacerta.modules.financialInstitution.domain.enums.FinancialInstitutionType;

import java.time.LocalDateTime;
import java.util.UUID;

public class FinancialInstitution {
    private UUID id;
    private Long connectorId;
    private String name;
    private String imageUrl;
    //private FinancialInstitutionType type;
    //private FinancialInstitutionHealth health;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public FinancialInstitution() { }

    public static FinancialInstitution create(
                                               Long connectorId,
                                               String name,
                                               String imageUrl
                                               ) {
        FinancialInstitution institution = new FinancialInstitution();
        institution.connectorId = connectorId;

        institution.name = name;
        institution.imageUrl = imageUrl;
       // institution.type  = bankType;
       // institution.health = health;

        institution.createdAt = LocalDateTime.now();
        return institution;
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public Long getConnectorId() { return connectorId; }
    public void setConnectorId(Long connectorId) { this.connectorId = connectorId; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }



    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}
