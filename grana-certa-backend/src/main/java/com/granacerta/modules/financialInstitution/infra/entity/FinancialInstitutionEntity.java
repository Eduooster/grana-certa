package com.granacerta.modules.financialInstitution.infra.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.mapstruct.Builder;

import java.util.UUID;

@Entity
@Table(name = "grana_certa_financial_institution")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class FinancialInstitutionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private Long connectorId;

    @Column(nullable = false)
    private String name;

    private String imageUrl;

    //private String health;

   // private String bankType;
}
