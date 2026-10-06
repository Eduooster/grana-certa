package com.granacerta.modules.financialConnection.infra.persistence.entity;

import com.granacerta.modules.financialConnection.domain.enums.FinancialConnectionStatus;
import com.granacerta.modules.financialInstitution.infra.entity.FinancialInstitutionEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(
        name = "grana_certa_financial_connection",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_financial_connection_provider_external_id",
                        columnNames = {"provider", "external_id"}
                )
        })
@Getter
@Setter
@NoArgsConstructor
public class FinancialConnectionEntity {

    @Id
     @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "user_id", nullable = false)
    private UUID userId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
            name = "financial_institution_id",
            nullable = false
    )
    private FinancialInstitutionEntity financialInstitution;

    @Column(nullable = false)
    private String provider;

    @Column(name = "external_id", nullable = false,unique = true)
    private String externalId;





    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FinancialConnectionStatus status;

    @Column(name = "connected_at", nullable = false)
    private LocalDateTime connectedAt;

    @Column(name = "last_synced_at")
    private LocalDateTime lastSyncedAt;
}
