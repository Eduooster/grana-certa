package com.granacerta.modules.financialConnection.infra.persistence.mapper;

import com.granacerta.modules.financialConnection.domain.entity.FinancialConnection;
import com.granacerta.modules.financialConnection.infra.persistence.entity.FinancialConnectionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface FinancialConnectionEntityMapper {
    @Mapping(target = "financialInstitution.id", source = "financialInstitutionId")
    FinancialConnectionEntity toEntity(FinancialConnection financialConnection);

    @Mapping(target = "financialInstitutionId", source = "financialInstitution.id")
    FinancialConnection toDomain(FinancialConnectionEntity entity);
}
