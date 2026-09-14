package com.granacerta.modules.category.domain.exception;

public class CategoryNotFoundException extends RuntimeException
{
    public CategoryNotFoundException(String message) {
        super(message);
    }
}
