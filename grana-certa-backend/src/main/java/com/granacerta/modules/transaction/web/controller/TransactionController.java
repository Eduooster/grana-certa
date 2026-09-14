package com.granacerta.modules.transaction.web.controller;

import com.granacerta.modules.transaction.application.usecase.*;
import com.granacerta.modules.transaction.web.dto.CreateTransactionRequest;
import com.granacerta.modules.transaction.web.dto.CreateTransactionResponse;
import com.granacerta.modules.transaction.web.dto.GetTransactionResponse;
import com.granacerta.modules.transaction.web.dto.GetTransactionsRequest;
import com.granacerta.modules.transaction.web.mapper.TransactionWebMapper;
import com.granacerta.security.userDetails.CustomUserDetails;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final CreateTransactionUseCase createTransactionUseCase;
    private final GetTransactionsUseCase getTransactionsUseCase;
    private final UpdateTransactionUseCase updateTransactionUseCase;

    private final GetTransactionUseCase getTransactionUseCase;
    private final DeleteTransactionUseCase deleteTransactionUseCase;
    private final TransactionWebMapper transactionWebMapper;

    @PostMapping
    public ResponseEntity<CreateTransactionResponse> create(
            @Valid @RequestBody CreateTransactionRequest request,@AuthenticationPrincipal CustomUserDetails customUserDetails
    ) {

        UUID userId = customUserDetails.getUserId();

        CreateTransactionCommand command =
                transactionWebMapper.toCreateCommand(request, userId);

        CreateTransactionResult result =
                createTransactionUseCase.execute(command);

        CreateTransactionResponse response =
                transactionWebMapper.toResponse(result);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping
    public ResponseEntity<Page<GetTransactionResponse>> getTransactions(
            @Valid @ModelAttribute GetTransactionsRequest request,
            @AuthenticationPrincipal CustomUserDetails customUserDetails
    ) {
        UUID userId =customUserDetails.getUserId() ;

        GetTransactionsCommand command = transactionWebMapper.toCommand(
                request,
                userId
        );

        Page<GetTransactionsResult> result =
                getTransactionsUseCase.execute(command);

        Page<GetTransactionResponse> response =
                result.map(transactionWebMapper::toResponse)
                ;

        return ResponseEntity.ok(response);
    }
    @GetMapping("/{id}")
    public ResponseEntity<GetTransactionResponse> getById(
            @PathVariable UUID id,@AuthenticationPrincipal CustomUserDetails customUserDetails
    ) {
        UUID userId = customUserDetails.getUserId();

        GetTransactionCommand command =
                new GetTransactionCommand(id, userId);

        GetTransactionResult result =
                getTransactionUseCase.execute(command);

        GetTransactionResponse response =
                transactionWebMapper.toResponse(result);

        return ResponseEntity.ok(response);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Void> update(
            @PathVariable UUID id,
            @Valid @RequestBody UpdateTransactionRequest request,@AuthenticationPrincipal CustomUserDetails customUserDetails
    ) {
        UUID userId = customUserDetails.getUserId();

        UpdateTransactionCommand command =
                transactionWebMapper.toCommand(request, id, userId);

        updateTransactionUseCase.execute(command);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable UUID id,@AuthenticationPrincipal  CustomUserDetails customUserDetails
    ) {
        UUID userId =  customUserDetails.getUserId();

        DeleteTransactionCommand command =
                new DeleteTransactionCommand(id, userId);

        deleteTransactionUseCase.execute(command);

        return ResponseEntity.noContent().build();
    }


}