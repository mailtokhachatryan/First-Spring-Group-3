package am.smartcode.first_spring.service.product.impl;

import am.smartcode.first_spring.exception.EntityNotFoundException;
import am.smartcode.first_spring.mapper.ProductMapper;
import am.smartcode.first_spring.model.dto.product.CreateProductDto;
import am.smartcode.first_spring.model.dto.product.ProductDto;
import am.smartcode.first_spring.model.dto.product.ProductFilter;
import am.smartcode.first_spring.model.entity.CategoryEntity;
import am.smartcode.first_spring.model.entity.ProductEntity;
import am.smartcode.first_spring.repository.CategoryRepository;
import am.smartcode.first_spring.repository.ProductRepository;
import am.smartcode.first_spring.service.product.ProductService;
import am.smartcode.first_spring.spec.ProductSpec;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final CategoryRepository categoryRepository;
    private final ProductSpec productSpec;


    @Override
    @Transactional
    public ProductDto create(CreateProductDto createProductDto) {
        ProductEntity entity = productMapper.toEntity(createProductDto);
        CategoryEntity categoryEntity = categoryRepository.
                findById(createProductDto.getCategoryId()).
                orElseThrow(() -> new EntityNotFoundException(""));
        entity.setCategory(categoryEntity);
        productRepository.save(entity);
        return productMapper.toDto(entity);

    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductDto> getAll(ProductFilter productFilter) {
        return productRepository.findAll(productSpec.filter(productFilter))
                .stream()
                .map(productMapper::toDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public ProductDto getById(Integer id) {
        ProductEntity productEntity = productRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(String.format("Product with id: %d not found.", id)));
        return productMapper.toDto(productEntity);
    }
}
