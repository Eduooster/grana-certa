package com.granacerta.modules.category.domain.repository;

import com.granacerta.modules.category.domain.entity.Category;
import com.granacerta.modules.category.domain.enums.CategoryStatus;

import java.util.Optional;
import java.util.UUID;

public interface CategoryRepository {
    Category save(Category category);

    Optional<Category> findByIdAndUserIdAndStatus(
            UUID categoryId,
            UUID userId,
            CategoryStatus status
    );

}
