package com.granacerta.modules.category.domain.repository;

import com.granacerta.modules.category.domain.entity.Category;
import com.granacerta.modules.category.domain.enums.CategorySource;
import com.granacerta.modules.category.domain.enums.CategoryStatus;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;
import java.util.UUID;

public interface CategoryRepository {
    Category save(Category category);

    Optional<Category> findByIdAndUserIdAndStatus(
            UUID categoryId,
            UUID userId,
            CategoryStatus status
    );

     Optional<Category> findByCodeAndCategorySource(String name, CategorySource categorySource);
}
