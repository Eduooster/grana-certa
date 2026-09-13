package com.granacerta.modules.category.infra.persistence.adapter;

import com.granacerta.modules.category.domain.entity.Category;
import com.granacerta.modules.category.domain.repository.CategoryRepository;
import com.granacerta.modules.category.infra.persistence.mapper.CategoryMapper;
import com.granacerta.modules.category.infra.persistence.repository.CategoryJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CategoryRepositoryImpl implements CategoryRepository {
    private final CategoryMapper categoryMapper;
    private final CategoryJpaRepository categoryJpaRepository;

    @Override
    public Category save(Category category) {
        return categoryMapper.toDomain(categoryJpaRepository.save(categoryMapper.toEntity(category)));
    }
}
