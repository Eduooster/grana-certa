package com.granacerta.modules.transfer.web.mapper;

import com.granacerta.modules.transfer.application.usecase.CreateTransferCommand;
import com.granacerta.modules.transfer.application.usecase.CreateTransferResult;
import com.granacerta.modules.transfer.application.usecase.UpdateTransferCommand;
import com.granacerta.modules.transfer.web.dto.CreateTransferRequest;
import com.granacerta.modules.transfer.web.dto.CreateTransferResponse;
import com.granacerta.modules.transfer.web.dto.UpdateTransferRequest;
import org.jspecify.annotations.Nullable;
import org.mapstruct.Mapper;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface TransferWebMapper {
    CreateTransferCommand toCommand(CreateTransferRequest request, UUID userId);
    UpdateTransferCommand toCommand(UpdateTransferRequest request, UUID transferId);

    CreateTransferResponse toResponse(CreateTransferResult result);
}
