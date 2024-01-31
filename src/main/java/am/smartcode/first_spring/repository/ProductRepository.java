package am.smartcode.first_spring.repository;

import am.smartcode.first_spring.model.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {
}