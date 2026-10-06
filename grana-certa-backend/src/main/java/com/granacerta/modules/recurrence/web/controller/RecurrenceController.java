package com.granacerta.modules.recurrence.web.controller;

import com.granacerta.modules.recurrence.application.usecase.CreateRecurrenceCommand;
import com.granacerta.modules.recurrence.application.usecase.CreateRecurrenceResult;
import com.granacerta.modules.recurrence.application.usecase.CreateRecurrenceUseCase;
import com.granacerta.modules.recurrence.web.dto.CreateRecurrenceRequest;
import com.granacerta.modules.recurrence.web.mapper.RecurrenceMapper;
import com.granacerta.security.userDetails.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/recurrence")
@RequiredArgsConstructor
public class RecurrenceController {
    private final CreateRecurrenceUseCase
    createRecurrenceUseCase;
    private final RecurrenceMapper recurrenceMapper;

    @PostMapping
    public ResponseEntity<Void> create(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @RequestBody CreateRecurrenceRequest request
    ) {
        CreateRecurrenceCommand command =
                recurrenceMapper.toCommand(
                        userDetails.getUserId(),
                        request
                );

        CreateRecurrenceResult result =
                createRecurrenceUseCase.execute(command);



        return ResponseEntity
                .status(HttpStatus.CREATED)
                .build();
    }
}
