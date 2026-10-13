package com.granacerta.modules.category.domain;


import com.granacerta.modules.category.domain.enums.CategoryType;
import com.granacerta.modules.category.domain.enums.SystemCategoryCode;

import java.util.List;

public final class SystemCategoryCatalog {

    private SystemCategoryCatalog() {
    }

    public static final List<SystemCategoryDefinition> CATEGORIES = List.of(
            new SystemCategoryDefinition(
                    SystemCategoryCode.SALARY,
                    "Salário",
                    CategoryType.INCOME
            ),
            new SystemCategoryDefinition(
                    SystemCategoryCode.HOUSING,
                    "Moradia",
                    CategoryType.EXPENSE
            ),
            new SystemCategoryDefinition(
                    SystemCategoryCode.UTILITIES,
                    "Contas de consumo",
                    CategoryType.EXPENSE
            ),
            new SystemCategoryDefinition(
                    SystemCategoryCode.TELECOMMUNICATIONS,
                    "Telecomunicações",
                    CategoryType.EXPENSE
            ),
            new SystemCategoryDefinition(
                    SystemCategoryCode.DIGITAL_SUBSCRIPTIONS,
                    "Assinaturas e serviços digitais",
                    CategoryType.EXPENSE
            )
    );
}
