package com.granacerta.modules.category.web.mapper;

import com.granacerta.modules.category.application.usecase.CreateCategoryCommand;
import com.granacerta.modules.category.application.usecase.CreateCategoryResult;
import com.granacerta.modules.category.web.dto.CategoryResponse;
import com.granacerta.modules.category.web.dto.CreateCategoryRequest;
import org.mapstruct.Mapper;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface CategoryWebMapper {
     CreateCategoryCommand toCreateCommand(CreateCategoryRequest request, UUID userId);

    CategoryResponse toResponse(CreateCategoryResult result);
}
