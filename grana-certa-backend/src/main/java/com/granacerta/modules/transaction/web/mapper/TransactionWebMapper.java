package com.granacerta.modules.transaction.web.mapper;

import com.granacerta.modules.transaction.application.usecase.*;
import com.granacerta.modules.transaction.web.dto.CreateTransactionRequest;
import com.granacerta.modules.transaction.web.dto.CreateTransactionResponse;
import com.granacerta.modules.transaction.web.dto.GetTransactionResponse;
import com.granacerta.modules.transaction.web.dto.GetTransactionsRequest;
import jakarta.validation.Valid;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface TransactionWebMapper {


    CreateTransactionCommand toCreateCommand(CreateTransactionRequest request, UUID userId);

    CreateTransactionResponse toResponse(CreateTransactionResult result);
    GetTransactionsCommand toCommand(GetTransactionsRequest request, UUID userId);
    GetTransactionResponse toResponse(GetTransactionResult result);




    GetTransactionResponse toResponse(
            GetTransactionsResult result
    );

    default Page<GetTransactionResponse> toResponse(
            Page<GetTransactionsResult> result
    ) {
        return result.map(this::toResponse);
    }


    UpdateTransactionCommand toCommand( UpdateTransactionRequest request, UUID id, UUID userId);
}
