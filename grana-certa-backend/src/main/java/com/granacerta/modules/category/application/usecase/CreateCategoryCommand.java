package com.granacerta.modules.category.application.usecase;

import com.granacerta.modules.category.domain.enums.CategoryType;

import java.util.UUID;

public record CreateCategoryCommand(
        UUID userId,
        UUID parentCategoryId,
        String name,
        CategoryType type
) {
}
