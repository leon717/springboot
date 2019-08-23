package com.leo.boot.jpa.spec;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaBuilder.In;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

public class Spec<T> implements Specification<T> {

    private static final long serialVersionUID = 1L;
    
    private SearchCriteria criteria;

    public Spec(SearchCriteria criteria) {
        this.criteria = criteria;
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        criteria.format(root.get(criteria.getKey()).getJavaType());
        if (criteria.getOperation().equalsIgnoreCase(">")) {
            return builder.greaterThanOrEqualTo(root.get(criteria.getKey()), (Comparable)criteria.getValue());
        } else if (criteria.getOperation().equalsIgnoreCase("<")) {
            return builder.lessThanOrEqualTo(root.get(criteria.getKey()), (Comparable)criteria.getValue());
        } else if (criteria.getOperation().equalsIgnoreCase(":")) {
            return builder.like(root.<String>get(criteria.getKey()), "%" + criteria.getValue() + "%");
        } else if ("=".equals(criteria.getOperation())) {
            return builder.equal(root.get(criteria.getKey()), criteria.getValue());
        } else if ("<>".equals(criteria.getOperation())) {
            return builder.notEqual(root.get(criteria.getKey()), criteria.getValue());
        } else if ("$".equals(criteria.getOperation())) {
            In<Object> in = builder.in(root.get(criteria.getKey()));
            ((List<?>)criteria.getValue()).stream().forEach(in::value);
            return in;
        }
        return null;
    }

}
