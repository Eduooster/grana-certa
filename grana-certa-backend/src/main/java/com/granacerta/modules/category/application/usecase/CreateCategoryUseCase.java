package com.granacerta.modules.category.application.usecase;

import com.granacerta.modules.category.domain.entity.Category;
import com.granacerta.modules.category.domain.repository.CategoryRepository;
import lombok.extern.slf4j.Slf4j;



public class CreateCategoryUseCase {
    private final CategoryRepository categoryRepository;

    public CreateCategoryUseCase(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    public CreateCategoryResult execute(CreateCategoryCommand command) {


        Category category = Category
                .create(command);

        Category savedCategory = categoryRepository.save(category);

        return new CreateCategoryResult(
                savedCategory.getId(),
                savedCategory.getUserId(),
                savedCategory.getParentCategoryId(),
                savedCategory.getName(),
                savedCategory.getType(),
                savedCategory.getStatus(),
                savedCategory.getCreatedAt(),
                savedCategory.getUpdatedAt()

        );
    }
}
