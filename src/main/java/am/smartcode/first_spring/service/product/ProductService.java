package am.smartcode.first_spring.service.product;

import am.smartcode.first_spring.model.dto.product.CreateProductDto;
import am.smartcode.first_spring.model.dto.product.ProductDto;

import java.util.List;

public interface ProductService {

    Integer create(CreateProductDto createProductDto);

    List<ProductDto> getAll();
}
