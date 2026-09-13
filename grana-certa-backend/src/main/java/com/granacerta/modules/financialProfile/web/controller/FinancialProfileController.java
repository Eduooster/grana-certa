package com.granacerta.modules.financialProfile.web.controller;



import com.granacerta.modules.financialProfile.application.usecase.*;
import com.granacerta.modules.financialProfile.web.dto.CreateFinancialProfileRequest;
import com.granacerta.modules.financialProfile.web.mapper.FinancialProfileWebMapper;
import com.granacerta.modules.financialProfile.web.dto.FinancialProfileResponse;
import com.granacerta.modules.financialProfile.web.dto.UpdateFinancialProfileRequest;
import com.granacerta.security.userDetails.CustomUserDetails;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/financial-profile")
@RequiredArgsConstructor
public class FinancialProfileController {

    private final GetFinancialProfileUseCase getFinancialProfileUseCase;
    private final UpdateFinancialProfileUseCase updateFinancialProfileUseCase;
    private final DeleteFinancialProfileUseCase deleteFinancialProfileUseCase;
    private final CreateFinancialProfileUseCase createFinancialProfileUseCase;
    private final FinancialProfileWebMapper financialProfileWebMapper;
    @PostMapping
    public ResponseEntity<FinancialProfileResponse> create(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @Valid @RequestBody CreateFinancialProfileRequest request
    ) {
        UUID userId = userDetails.getUserId();

        CreateFinancialProfileCommand createFinancialProfileCommand =
                financialProfileWebMapper.toCreateCommand(request, userId);

        CreateFinancialProfileResult result =
                createFinancialProfileUseCase.execute(createFinancialProfileCommand);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(result.id()
                )
                .toUri();


        return ResponseEntity.created(location).build();
    }

    @GetMapping
    public ResponseEntity<FinancialProfileResponse> get(
            @AuthenticationPrincipal CustomUserDetails userDetails
    ) {
        UUID userId = userDetails.getUserId();

        GetFinancialProfileCommand getFinancialProfileCommand =
                financialProfileWebMapper.toGetCommand(userId);

        GetFinancialProfileResult getFinancialProfileResult =
                getFinancialProfileUseCase.execute(getFinancialProfileCommand);

        FinancialProfileResponse financialProfileResponse =
                financialProfileWebMapper.toResponse(
                        getFinancialProfileResult
                );

        return ResponseEntity.ok(financialProfileResponse);
    }


    @PutMapping
    public ResponseEntity<FinancialProfileResponse> update(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @Valid @RequestBody UpdateFinancialProfileRequest request
    ) {
        UUID userId = userDetails.getUserId();

        UpdateFinancialProfileCommand updateFinancialProfileCommand =
                financialProfileWebMapper.toUpdateCommand(request, userId);

        UpdateFinancialProfileResult updateFinancialProfileResult =
                updateFinancialProfileUseCase.execute(
                        updateFinancialProfileCommand
                );

        FinancialProfileResponse financialProfileResponse =
                financialProfileWebMapper.toResponse(
                        updateFinancialProfileResult
                );

        return ResponseEntity.ok(financialProfileResponse);
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(
            @AuthenticationPrincipal CustomUserDetails userDetails
    ) {
        UUID userId = userDetails.getUserId();

        DeleteFinancialProfileCommand deleteFinancialProfileCommand =
                financialProfileWebMapper.toDeleteCommand(userId);

        deleteFinancialProfileUseCase.execute(deleteFinancialProfileCommand);

        return ResponseEntity.noContent().build();
    }
}
