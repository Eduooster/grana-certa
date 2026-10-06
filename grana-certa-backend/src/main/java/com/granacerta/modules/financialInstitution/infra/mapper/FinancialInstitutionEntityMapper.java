package com.granacerta.modules.financialInstitution.infra.mapper;

import com.granacerta.modules.financialInstitution.domain.entity.FinancialInstitution;
import com.granacerta.modules.financialInstitution.infra.entity.FinancialInstitutionEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FinancialInstitutionEntityMapper {

    FinancialInstitution toDomain (FinancialInstitutionEntity entity);
    FinancialInstitutionEntity toEntity (FinancialInstitution domain);
}
