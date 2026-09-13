package com.granacerta.modules.category.domain.entity;

import com.granacerta.modules.category.application.usecase.CreateCategoryCommand;
import com.granacerta.modules.category.domain.enums.CategoryStatus;
import com.granacerta.modules.category.domain.enums.CategoryType;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class Category {
    private UUID id;
    private UUID userId;
    private UUID parentCategoryId;
    private String name;
    private CategoryType type;
    private CategoryStatus status;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


    public static Category create(CreateCategoryCommand command) {

        Category category = new Category();
        category.userId = command.userId();
        category.parentCategoryId = command.parentCategoryId();
        category.name = command.name();
        category.type = command.type();
        category.status = CategoryStatus.ACTIVE;

        return category;


    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public UUID getParentCategoryId() {
        return parentCategoryId;
    }

    public void setParentCategoryId(UUID parentCategoryId) {
        this.parentCategoryId = parentCategoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CategoryType getType() {
        return type;
    }

    public void setType(CategoryType type) {
        this.type = type;
    }

    public CategoryStatus getStatus() {
        return status;
    }

    public void setStatus(CategoryStatus status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

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