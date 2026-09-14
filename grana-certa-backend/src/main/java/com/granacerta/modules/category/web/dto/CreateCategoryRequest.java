package com.granacerta.modules.category.web.dto;

import com.granacerta.modules.category.domain.enums.CategoryType;

import java.util.UUID;

public record CreateCategoryRequest(UUID parentCategoryId,
                                    String name,
                                    CategoryType type) {
}
