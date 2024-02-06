package am.smartcode.first_spring.service.category.impl;

import am.smartcode.first_spring.exception.EntityNotFoundException;
import am.smartcode.first_spring.mapper.CategoryMapper;
import am.smartcode.first_spring.model.dto.category.CategoryDto;
import am.smartcode.first_spring.model.dto.category.CreateCategoryDto;
import am.smartcode.first_spring.model.dto.category.UpdateCategoryDto;
import am.smartcode.first_spring.model.entity.CategoryEntity;
import am.smartcode.first_spring.repository.CategoryRepository;
import am.smartcode.first_spring.service.category.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    @Transactional
    public CategoryDto create(CreateCategoryDto createCategoryDto) {
        CategoryEntity entity = categoryMapper.toEntity(createCategoryDto);
        categoryRepository.save(entity);
        return categoryMapper.toDto(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CategoryDto> getAll() {
        return categoryRepository.findAll().stream().map(categoryMapper::toDto).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public CategoryDto getById(Integer id) {
        CategoryEntity categoryEntity = categoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Product with id: %d not found.", id)));
        return categoryMapper.toDto(categoryEntity);
    }

    @Override
    @Transactional
    public CategoryDto update(UpdateCategoryDto updateCategoryDto, int id) {
        CategoryEntity categoryEntity = categoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Product with id: %d not found.", id)));
        categoryMapper.update(categoryEntity, updateCategoryDto);
        categoryRepository.save(categoryEntity);
        return categoryMapper.toDto(categoryEntity);

    }

    @Override
    @Transactional
    public void delete(int id) {
        categoryRepository.deleteById(id);
    }
}
