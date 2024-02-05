package am.smartcode.first_spring.service.category;

import am.smartcode.first_spring.model.dto.category.CreateCategoryDto;
import am.smartcode.first_spring.model.dto.category.CategoryDto;
import am.smartcode.first_spring.model.dto.category.UpdateCategoryDto;
import am.smartcode.first_spring.model.dto.product.ProductDto;
import am.smartcode.first_spring.model.dto.product.ProductFilter;
import am.smartcode.first_spring.model.dto.user.UpdateUserDto;
import am.smartcode.first_spring.model.dto.user.UserDto;

import java.util.List;

public interface CategoryService {
    CategoryDto create(CreateCategoryDto createCategoryDto);

    List<CategoryDto> getAll();

    CategoryDto getById(Integer id);
    CategoryDto update(UpdateCategoryDto updateCategoryDto, int id);
    void delete(int id);

}
