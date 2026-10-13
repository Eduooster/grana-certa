package com.granacerta.modules.category.infra.persistence.repository;

import com.granacerta.modules.category.domain.entity.Category;
import com.granacerta.modules.category.domain.enums.CategorySource;
import com.granacerta.modules.category.domain.enums.CategoryStatus;
import com.granacerta.modules.category.infra.persistence.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CategoryJpaRepository extends JpaRepository<CategoryEntity, UUID> {

    Optional<CategoryEntity> findByIdAndUserIdAndStatus(
            UUID categoryId,
            UUID userId,
            CategoryStatus status
    );

    Optional<CategoryEntity> findByCodeAndCategorySource(String name, CategorySource categorySource);
}
