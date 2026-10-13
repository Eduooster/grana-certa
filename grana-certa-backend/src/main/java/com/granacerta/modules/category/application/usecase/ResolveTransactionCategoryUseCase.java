package com.granacerta.modules.category.application.usecase;


import com.granacerta.modules.category.domain.TransactionCategoryClassifier;
import com.granacerta.modules.category.domain.entity.Category;
import com.granacerta.modules.category.domain.enums.CategorySource;
import com.granacerta.modules.category.domain.enums.SystemCategoryCode;
import com.granacerta.modules.category.domain.repository.CategoryRepository;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;
import java.util.UUID;

@Slf4j
public class ResolveTransactionCategoryUseCase {

    private final TransactionCategoryClassifier classifier;
    private final CategoryRepository categoryRepository;

    public ResolveTransactionCategoryUseCase(
            TransactionCategoryClassifier classifier,
            CategoryRepository categoryRepository
    ) {
        this.classifier = classifier;
        this.categoryRepository = categoryRepository;
    }



    public Optional<UUID> execute(String description) {


        Optional<SystemCategoryCode> categoryCode =
                classifier.classify(description);

        if (categoryCode.isEmpty()) {

            return Optional.empty();
        }

        String code = categoryCode.get().name();



        Optional<Category> category =
                categoryRepository.findByCodeAndCategorySource(
                        code,
                        CategorySource.SYSTEM
                );

        if (category.isEmpty()) {

            return Optional.empty();
        }

        UUID categoryId = category.get().getId();



        return Optional.of(categoryId);
    }
}
