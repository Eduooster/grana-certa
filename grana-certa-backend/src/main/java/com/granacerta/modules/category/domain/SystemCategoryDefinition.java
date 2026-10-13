package com.granacerta.modules.category.domain;


import com.granacerta.modules.category.domain.enums.CategoryType;
import com.granacerta.modules.category.domain.enums.SystemCategoryCode;

public record SystemCategoryDefinition(
        SystemCategoryCode code,
        String name,
        CategoryType type
) {
}