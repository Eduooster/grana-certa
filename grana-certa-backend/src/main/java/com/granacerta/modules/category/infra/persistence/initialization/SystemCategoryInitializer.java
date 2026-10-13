package com.granacerta.modules.category.infra.persistence.initialization;


import com.granacerta.modules.category.domain.SystemCategoryCatalog;
import com.granacerta.modules.category.domain.SystemCategoryDefinition;
import com.granacerta.modules.category.domain.entity.Category;
import com.granacerta.modules.category.domain.repository.CategoryRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class SystemCategoryInitializer implements ApplicationRunner {

    private final CategoryRepository categoryRepository;

    public SystemCategoryInitializer(
            CategoryRepository categoryRepository
    ) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void run(ApplicationArguments args) {
        for (SystemCategoryDefinition definition
                : SystemCategoryCatalog.CATEGORIES) {

            Category category = Category.createSystemCategory(
                    definition.name(),
                    definition.code().name(),
                    definition.type()
            );

            categoryRepository.save(category);
        }
    }
}
