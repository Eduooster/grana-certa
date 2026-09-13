package com.granacerta.modules.category.infra.persistence.mapper;


import com.granacerta.modules.category.domain.entity.Category;
import com.granacerta.modules.category.infra.persistence.entity.CategoryEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring" )
public interface CategoryMapper {

    Category toDomain(CategoryEntity entity);
    CategoryEntity toEntity(Category domain);

}
