package com.clemer.stock.domain.specification;

import com.clemer.stock.domain.entities.Category;
import com.clemer.stock.domain.entities.Product;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

public class ProductSpecification {

    public static Specification<Product> hasName(String name) {
        return (root, query, criteriaBuilder) ->
                name == null ? null : criteriaBuilder.like(root.get("name"), "%" + name + "%");
    }

    public static Specification<Product> hasCategory(List<Category> categories) {
        return (root, query, criteriaBuilder) -> {
            if (categories == null || categories.isEmpty()) {
                return criteriaBuilder.conjunction(); // Return a no-op predicate if no categories are provided
            }
            // Use `in` to create the predicate for the list of categories
            return root.get("category").in(categories);
        };
    }

    public static Specification<Product> hasPriceRange(BigDecimal minPrice, BigDecimal maxPrice) {
        return (root, query, criteriaBuilder) -> {
            if (minPrice == null && maxPrice == null) {
                return null;
            } else if (minPrice == null) {
                return criteriaBuilder.lessThanOrEqualTo(root.get("price"), maxPrice);
            } else if (maxPrice == null) {
                return criteriaBuilder.greaterThanOrEqualTo(root.get("price"), minPrice);
            } else {
                return criteriaBuilder.between(root.get("price"), minPrice, maxPrice);
            }
        };
    }

    public static Specification<Product> isAvailable(Boolean isAvailable) {
        return (root, query, criteriaBuilder) -> {
        if(Objects.isNull(isAvailable)){
            return null;
        }
          if(isAvailable){
              return criteriaBuilder.greaterThan(root.get("stock"), 0);
          } else {
              return criteriaBuilder.lessThanOrEqualTo(root.get("stock"), 0);
          }
        };
    }
}
