package com.geekbrains.ru.springmvcdemo.repository.specification;

import com.geekbrains.ru.springmvcdemo.domain.Product;
import com.geekbrains.ru.springmvcdemo.domain.search.ProductSearchConditional;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class ProductSearchSpecification implements Specification<Product> {

    ProductSearchConditional searchConditional;

    @Override
    public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        List<Predicate> predicates = new ArrayList<>();

        if (searchConditional.getMaxPrice() != null || searchConditional.getMinPrice() != null) {

            if (searchConditional.getMaxPrice() != null && searchConditional.getMinPrice() != null) {
                predicates.add(builder.between(root.get("price"),
                        searchConditional.getMinPrice(), searchConditional.getMaxPrice()));
            } else if (searchConditional.getMinPrice() != null) {
                predicates.add(builder.greaterThanOrEqualTo(root.get("price"), searchConditional.getMinPrice()));
            } else {
                predicates.add(builder.lessThanOrEqualTo(root.get("price"), searchConditional.getMaxPrice()));
            }

        }

        return builder.and(predicates.toArray(Predicate[]::new));
    }
}
