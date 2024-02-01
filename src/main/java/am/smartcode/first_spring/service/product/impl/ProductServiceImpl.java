package am.smartcode.first_spring.service.product.impl;

import am.smartcode.first_spring.exception.EntityNotFoundException;
import am.smartcode.first_spring.mapper.ProductMapper;
import am.smartcode.first_spring.model.dto.product.CreateProductDto;
import am.smartcode.first_spring.model.dto.product.ProductDto;
import am.smartcode.first_spring.model.entity.ProductEntity;
import am.smartcode.first_spring.repository.ProductRepository;
import am.smartcode.first_spring.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;


    @Override
    @Transactional
    public Integer create(CreateProductDto createProductDto) {
        ProductEntity entity = productMapper.toEntity(createProductDto);
        productRepository.save(entity);
        return entity.getId();
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductDto> getAll() {
        return productRepository.findAll().stream().map(productMapper::toDto).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public ProductDto getById(Integer id) {
        ProductEntity productEntity = productRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(String.format("Product with id: %d not found.", id)));
        return productMapper.toDto(productEntity);
    }


}
