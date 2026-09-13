package com.granacerta.modules.category.web.controller;

import com.granacerta.modules.category.application.usecase.CreateCategoryCommand;
import com.granacerta.modules.category.application.usecase.CreateCategoryResult;
import com.granacerta.modules.category.application.usecase.CreateCategoryUseCase;
import com.granacerta.modules.category.web.mapper.CategoryWebMapper;
import com.granacerta.modules.category.web.dto.CategoryResponse;
import com.granacerta.modules.category.web.dto.CreateCategoryRequest;
import com.granacerta.security.userDetails.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CreateCategoryUseCase createCategoryUseCase;
    private final CategoryWebMapper categoryWebMapper;

    @PostMapping
    public ResponseEntity<CategoryResponse> create(
            @RequestBody CreateCategoryRequest request, @AuthenticationPrincipal CustomUserDetails userPrincipal
    ) {
        CreateCategoryCommand command = categoryWebMapper.toCreateCommand(request,userPrincipal.getUserId());

        CreateCategoryResult result = createCategoryUseCase.execute(command);

        CategoryResponse response = categoryWebMapper.toResponse(result);

        URI location = URI.create("/api/v1/categories/" + response.id());

        return ResponseEntity
                .created(location)
                .body(response);
    }
}