package com.granacerta;

import com.granacerta.modules.category.domain.entity.Category;
import com.granacerta.modules.category.domain.enums.CategorySource;
import com.granacerta.modules.category.domain.enums.CategoryStatus;
import com.granacerta.modules.category.domain.enums.CategoryType;
import com.granacerta.modules.category.domain.repository.CategoryRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class GranaCertaBackendApplicationTests {

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    void contextLoads() {
    }

    @Test
    void persistsAndResolvesHousingSystemCategory() {
        Category category = categoryRepository
                .findByCodeAndCategorySource("HOUSING", CategorySource.SYSTEM)
                .orElseThrow();

        Assertions.assertEquals("HOUSING", category.getCode());
        Assertions.assertEquals("Moradia", category.getName());
        Assertions.assertEquals(CategorySource.SYSTEM, category.getCategorySource());
        Assertions.assertEquals(CategoryType.EXPENSE, category.getType());
        Assertions.assertEquals(CategoryStatus.ACTIVE, category.getStatus());
        Assertions.assertNull(category.getUserId());
    }

}
