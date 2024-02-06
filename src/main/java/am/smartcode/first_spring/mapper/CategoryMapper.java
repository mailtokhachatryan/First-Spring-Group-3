package am.smartcode.first_spring.mapper;

import am.smartcode.first_spring.model.dto.category.CategoryDto;
import am.smartcode.first_spring.model.dto.category.CreateCategoryDto;
import am.smartcode.first_spring.model.dto.category.UpdateCategoryDto;
import am.smartcode.first_spring.model.dto.product.CreateProductDto;
import am.smartcode.first_spring.model.dto.product.ProductDto;
import am.smartcode.first_spring.model.dto.user.UpdateUserDto;
import am.smartcode.first_spring.model.entity.CategoryEntity;
import am.smartcode.first_spring.model.entity.ProductEntity;
import am.smartcode.first_spring.model.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")

public interface CategoryMapper {
    CategoryEntity toEntity(CreateCategoryDto createCategoryDto);

    CategoryDto toDto(CategoryEntity categoryEntity);

    void update(@MappingTarget CategoryEntity categoryEntity, UpdateCategoryDto updateCategoryDto);

}
