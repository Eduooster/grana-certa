package com.granacerta.modules.category.web.dto;

import com.granacerta.modules.category.domain.enums.CategoryStatus;
import com.granacerta.modules.category.domain.enums.CategoryType;

import java.time.LocalDateTime;
import java.util.UUID;

public record CategoryResponse(
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
