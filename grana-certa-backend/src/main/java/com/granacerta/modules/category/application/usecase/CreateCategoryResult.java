package com.granacerta.modules.category.application.usecase;

import com.granacerta.modules.category.domain.enums.CategoryStatus;
import com.granacerta.modules.category.domain.enums.CategoryType;

import java.time.LocalDateTime;
import java.util.UUID;

public record CreateCategoryResult(
        UUID id,
        UUID userId,
        UUID parentCategoryId,
        String name,
        CategoryType type,
        CategoryStatus status,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
