package com.granacerta.modules.financialInstitution.web.mapper;

import com.granacerta.modules.financialInstitution.application.usecase.CreateFinancialInstitutionCommand;
import com.granacerta.modules.financialInstitution.web.dto.FinancialInstitutionRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FinancialInstitutionWebMapper {
    CreateFinancialInstitutionCommand toCommand(FinancialInstitutionRequest request);
}
