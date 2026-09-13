package com.granacerta.modules.financialProfile.web.mapper;

import com.granacerta.modules.financialProfile.application.usecase.*;
import com.granacerta.modules.financialProfile.web.dto.CreateFinancialProfileRequest;
import com.granacerta.modules.financialProfile.web.dto.FinancialProfileResponse;
import com.granacerta.modules.financialProfile.web.dto.UpdateFinancialProfileRequest;
import org.mapstruct.Mapper;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface FinancialProfileWebMapper {
    CreateFinancialProfileCommand toCreateCommand( CreateFinancialProfileRequest request, UUID userId);



    GetFinancialProfileCommand toGetCommand(UUID userId);
    FinancialProfileResponse toResponse(CreateFinancialProfileResult result);

    FinancialProfileResponse toResponse(GetFinancialProfileResult result);

    FinancialProfileResponse toResponse(UpdateFinancialProfileResult result);

    DeleteFinancialProfileCommand toDeleteCommand(UUID userId);

    UpdateFinancialProfileCommand toUpdateCommand(UpdateFinancialProfileRequest request, UUID userId);
}
