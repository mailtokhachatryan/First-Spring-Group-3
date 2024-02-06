package am.smartcode.first_spring.spec;

import am.smartcode.first_spring.model.dto.product.ProductFilter;
import am.smartcode.first_spring.model.entity.ProductEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.Predicate;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class ProductSpec {

    public Specification<ProductEntity> filter(ProductFilter productFilter){
        return Specification.where((root, query, criteriaBuilder) -> {

            List<Predicate> predicates = new ArrayList<>();
            Predicate filterPredicate;

            BigDecimal startPrice = productFilter.getStartPrice();
            if (Objects.nonNull(startPrice)) {
                filterPredicate = criteriaBuilder
                        .greaterThanOrEqualTo(root.get("price"), startPrice);
                predicates.add(filterPredicate);
            }

            BigDecimal endPrice = productFilter.getEndPrice();
            if (Objects.nonNull(endPrice)) {
                filterPredicate = criteriaBuilder
                        .lessThanOrEqualTo(root.get("price"), endPrice);
                predicates.add(filterPredicate);
            }

            Integer categoryId = productFilter.getCategoryId();
            if (categoryId != null) {
                filterPredicate = criteriaBuilder.equal(root.get("category"), categoryId);
                predicates.add(filterPredicate);
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        });
    }

}
