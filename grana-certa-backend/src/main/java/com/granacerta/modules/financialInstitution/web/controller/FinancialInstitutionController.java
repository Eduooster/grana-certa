package com.granacerta.modules.financialInstitution.web.controller;

import com.granacerta.modules.financialInstitution.application.usecase.CreateFinancialInstitutionUseCase;
import com.granacerta.modules.financialInstitution.web.dto.FinancialInstitutionRequest;
import com.granacerta.modules.financialInstitution.web.mapper.FinancialInstitutionWebMapper;
import com.granacerta.security.userDetails.CustomUserDetails;
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
@RequestMapping("/api/v1/financial-institutions")
@RequiredArgsConstructor
public class FinancialInstitutionController {
    private final CreateFinancialInstitutionUseCase createFinancialInstitutionUseCase;
    private final FinancialInstitutionWebMapper financialInstitutionWebMapper;

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody FinancialInstitutionRequest request,@AuthenticationPrincipal CustomUserDetails user) {
        UUID financialInstitutionId = createFinancialInstitutionUseCase.execute(
                financialInstitutionWebMapper.toCommand(request)
        );
        URI location = URI.create("/api/v1/financial-institutions/" + financialInstitutionId);
        return ResponseEntity.created(location).build();
    }
}
