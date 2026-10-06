package com.granacerta.modules.transfer.web.controller;

import com.granacerta.modules.transfer.application.usecase.*;
import com.granacerta.modules.transfer.web.dto.CreateTransferRequest;
import com.granacerta.modules.transfer.web.dto.CreateTransferResponse;
import com.granacerta.modules.transfer.web.dto.UpdateTransferRequest;
import com.granacerta.modules.transfer.web.mapper.TransferWebMapper;
import com.granacerta.security.userDetails.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/tranfer")
@RequiredArgsConstructor
public class TranferController {

    private final CreateTransferUseCase createTransferUseCase;
    private final UpdateTransferUseCase updateTransferUseCase;
    private final TransferWebMapper transferWebMapper;

    @PostMapping
    public ResponseEntity<CreateTransferResponse> create(
            @RequestBody CreateTransferRequest request, @AuthenticationPrincipal CustomUserDetails  userDetails
            ) {
        CreateTransferCommand command = transferWebMapper.toCommand(request,userDetails.getUserId());

        CreateTransferResult result = createTransferUseCase.execute(command);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(transferWebMapper.toResponse(result));
    }
    @PatchMapping("/{id}")
    public ResponseEntity<Void> update(
            @PathVariable UUID id,
            @RequestBody UpdateTransferRequest request
    ) {
        UpdateTransferCommand command = transferWebMapper.toCommand(

                request,id
        );

        updateTransferUseCase.execute(command);

        return ResponseEntity.noContent().build();
    }



}
