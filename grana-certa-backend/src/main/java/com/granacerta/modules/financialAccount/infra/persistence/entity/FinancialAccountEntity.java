package com.granacerta.modules.financialAccount.infra.persistence.entity;

import com.granacerta.modules.financialAccount.domain.enums.FinancialAccountSource;
import com.granacerta.modules.financialAccount.domain.enums.FinancialAccountStatus;
import com.granacerta.modules.financialAccount.domain.enums.FinancialAccountType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "grana_certa_financial_account")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FinancialAccountEntity {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "user_id", nullable = false)
    private UUID userId;

    @Column(name = "connection_id")
    private UUID connectionId;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FinancialAccountType type;

    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal balance;

    @Column(name = "initial_balance", nullable = false, precision = 19, scale = 2)
    private BigDecimal initialBalance;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FinancialAccountStatus status;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FinancialAccountSource source;

    @Column(name = "external_id")
    private String externalId;

    @Column(name = "created_at", nullable = false)

    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    @UpdateTimestamp
    private LocalDateTime updatedAt;
}