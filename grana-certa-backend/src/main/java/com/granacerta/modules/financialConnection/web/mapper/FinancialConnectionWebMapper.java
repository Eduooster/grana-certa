package com.granacerta.modules.financialConnection.web.mapper;

import com.granacerta.modules.financialConnection.application.usecase.CreateFinancialConnectionCommand;
import com.granacerta.modules.financialConnection.web.dto.CreateFinancialConnectionRequest;
import org.mapstruct.Mapper;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface FinancialConnectionWebMapper {
    CreateFinancialConnectionCommand toCommand(CreateFinancialConnectionRequest request, UUID userId);
}
