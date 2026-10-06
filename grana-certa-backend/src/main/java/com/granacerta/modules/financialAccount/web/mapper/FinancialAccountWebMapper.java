package com.granacerta.modules.financialAccount.web.mapper;

import com.granacerta.modules.financialAccount.application.usecase.CreateFinancialAccountCommand;
import com.granacerta.modules.financialAccount.application.usecase.CreateFinancialAccountResult;
import com.granacerta.modules.financialAccount.web.dto.CreateFinancialAccountRequest;
import com.granacerta.modules.financialAccount.web.dto.CreateFinancialAccountResponse;
import org.jspecify.annotations.Nullable;
import org.mapstruct.Mapper;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface FinancialAccountWebMapper {
    CreateFinancialAccountCommand toCommand(CreateFinancialAccountRequest request, UUID userId);

     CreateFinancialAccountResponse toResponse(CreateFinancialAccountResult result);
}
