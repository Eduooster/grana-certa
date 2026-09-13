package com.granacerta.modules.category.infra.persistence.repository;

import com.granacerta.modules.category.infra.persistence.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CategoryJpaRepository extends JpaRepository<CategoryEntity, UUID> {
}
