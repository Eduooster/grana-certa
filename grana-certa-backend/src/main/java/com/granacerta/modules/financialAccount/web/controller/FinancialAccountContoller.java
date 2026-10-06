package com.granacerta.modules.financialAccount.web.controller;

import com.granacerta.modules.financialAccount.application.usecase.CreateFinancialAccountCommand;
import com.granacerta.modules.financialAccount.application.usecase.CreateFinancialAccountResult;
import com.granacerta.modules.financialAccount.application.usecase.CreateFinancialAccountUseCase;
import com.granacerta.modules.financialAccount.web.dto.CreateFinancialAccountRequest;
import com.granacerta.modules.financialAccount.web.dto.CreateFinancialAccountResponse;
import com.granacerta.modules.financialAccount.web.mapper.FinancialAccountWebMapper;
import com.granacerta.modules.transaction.application.usecase.UpdateTransactionCommand;
import com.granacerta.modules.transaction.application.usecase.UpdateTransactionRequest;
import com.granacerta.security.userDetails.CustomUserDetails;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequiredArgsConstructor
@RequestMapping("/api/v1/financial-account")
@RestController
public class FinancialAccountContoller {

    private final CreateFinancialAccountUseCase createFinancialAccountUseCase;
    private final FinancialAccountWebMapper financialAccountWebMapper;

    @PostMapping
    public ResponseEntity<CreateFinancialAccountResponse> create(

            @Valid
            @RequestBody CreateFinancialAccountRequest request,@AuthenticationPrincipal CustomUserDetails customUserDetails
    ) {
        CreateFinancialAccountCommand command = financialAccountWebMapper.toCommand(request,customUserDetails.getUserId());

        CreateFinancialAccountResult result = createFinancialAccountUseCase.execute(command);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(financialAccountWebMapper.toResponse(result));
    }


}
