package com.granacerta.modules.category.domain.enums;


public enum SystemCategoryCode {


    SALARY(CategoryType.INCOME),


    HOUSING(CategoryType.EXPENSE),
    UTILITIES(CategoryType.EXPENSE),
    TELECOMMUNICATIONS(CategoryType.EXPENSE),
    DIGITAL_SUBSCRIPTIONS(CategoryType.EXPENSE);

    private final CategoryType type;

    SystemCategoryCode(CategoryType type) {
        this.type = type;
    }

    public CategoryType getType() {
        return type;
    }
}
