package com.granacerta.modules.financialProfile.infra.persistence.entity;



import com.granacerta.modules.financialProfile.domain.enums.ControlLevel;
import com.granacerta.modules.financialProfile.domain.enums.MainDifficulty;
import com.granacerta.modules.financialProfile.domain.enums.Objective;
import com.granacerta.modules.financialProfile.domain.enums.PreferredView;
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
@Table(name = "grana_certa_financial_profile")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FinancialProfileEntity {
    @Id
    @GeneratedValue
    private UUID id;


    @Column(name = "user_id")
    private UUID userId;


    @Enumerated(EnumType.STRING)
    private Objective objective;

    @Column(name = "control_level")
    @Enumerated(EnumType.STRING)
    private ControlLevel controlLevel;

    @Column(name = "main_difficulty")
    @Enumerated(EnumType.STRING)
    private MainDifficulty mainDifficulty;

    @Column(name = "preferred_view")
    @Enumerated(EnumType.STRING)
    private PreferredView preferredView;
    @Column(name = "monthly_income")
    private BigDecimal monthlyIncome;

    @Column(name = "created_at", nullable = false)

    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)



    @UpdateTimestamp
    private LocalDateTime updatedAt;
}