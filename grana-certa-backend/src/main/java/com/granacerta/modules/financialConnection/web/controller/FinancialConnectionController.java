package com.granacerta.modules.financialConnection.web.controller;

import com.granacerta.modules.financialConnection.application.usecase.CreateFinancialConnectionUseCase;
import com.granacerta.modules.financialConnection.application.usecase.GenerateConnectionTokenCommand;
import com.granacerta.modules.financialConnection.application.usecase.GenerateConnectionTokenUseCase;
import com.granacerta.modules.financialConnection.infra.persistence.external.pluggy.client.dto.GenerateConnectionTokenResponse;

import com.granacerta.modules.financialConnection.web.dto.CreateFinancialConnectionRequest;
import com.granacerta.modules.financialConnection.web.mapper.FinancialConnectionWebMapper;
import com.granacerta.security.userDetails.CustomUserDetails;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/financial-connections")
@RequiredArgsConstructor
public class FinancialConnectionController {

    private final GenerateConnectionTokenUseCase generateConnectionTokenUseCase;
    private final CreateFinancialConnectionUseCase createFinancialConnectionUseCase;
    private final FinancialConnectionWebMapper financialConnectionWebMapper;

    @PostMapping
    public ResponseEntity<Void> create(
            @Valid @RequestBody CreateFinancialConnectionRequest request,
            @AuthenticationPrincipal CustomUserDetails userDetails
    ) {
        UUID financialConnectionId = createFinancialConnectionUseCase.execute(
                financialConnectionWebMapper.toCommand(request, userDetails.getUserId())
        );
        URI location = URI.create("/api/v1/financial-connections/" + financialConnectionId);
        return ResponseEntity.created(location).build();
    }

    @PostMapping("/connect-token")
    public ResponseEntity<GenerateConnectionTokenResponse> generateConnectionToken(
            @AuthenticationPrincipal CustomUserDetails userDetails
    ) {

        GenerateConnectionTokenCommand command =
                new GenerateConnectionTokenCommand(userDetails.getUserId());

        String accessToken = generateConnectionTokenUseCase.execute(command);

        GenerateConnectionTokenResponse response =
                new GenerateConnectionTokenResponse(accessToken);

        return ResponseEntity.ok(response);
    }





}
