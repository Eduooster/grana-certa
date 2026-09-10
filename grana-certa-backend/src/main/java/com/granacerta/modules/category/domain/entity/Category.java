package com.granacerta.modules.category.domain.entity;

import java.time.LocalDateTime;
import java.util.Objects;

public class Category {
    private Long id;
    private Long userId;
    private Long parentCategoryId;
    private String name;
    private String type;
    private String status;
    private boolean isDefault;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return Objects.equals(id, category.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}