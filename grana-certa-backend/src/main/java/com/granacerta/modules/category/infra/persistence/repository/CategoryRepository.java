package com.granacerta.modules.category.infra.persistence.repository;


import com.granacerta.modules.category.infra.persistence.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository  extends JpaRepository<CategoryEntity, Long> {
}
