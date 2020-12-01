package com.leo.boot.jpa.stream;

import org.apache.commons.beanutils.PropertyUtils;
import org.hibernate.query.criteria.internal.expression.LiteralExpression;
import org.hibernate.query.criteria.internal.path.SingularAttributePath;
import org.hibernate.query.criteria.internal.predicate.ComparisonPredicate;
import org.hibernate.query.criteria.internal.predicate.ComparisonPredicate.ComparisonOperator;
import org.hibernate.query.criteria.internal.predicate.CompoundPredicate;
import org.hibernate.query.criteria.internal.predicate.InPredicate;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;

import javax.persistence.criteria.Predicate.BooleanOperator;
import java.lang.reflect.InvocationTargetException;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * @throws ClassCastException when compare
 * @throws RuntimeException when can't get property
 */
public class JPAStreamConverter {

    public static <T> Stream<T> setPageable(Stream<T> stream, Pageable pageable) {
        if (pageable.getSort() != null) {
            Comparator<T> comparator = StreamSupport.stream(pageable.getSort().spliterator(), false)
                    .map(JPAStreamConverter::<T>convert).reduce(Comparator::thenComparing).get();
            stream.sorted(comparator);
        }

        int pageNumber = pageable.getPageNumber();
        int pageSize = pageable.getPageSize();
        return stream.skip(pageNumber * pageSize).limit(pageSize);
    }

    public static <T> Comparator<T> convert(Order order) {
        Direction direction = order.getDirection();
        String property = order.getProperty();
        Comparator<T> comparator = Comparator.comparing(f -> (Comparable) getProperty(f, property));
        return Direction.ASC.equals(direction) ? comparator : comparator.reversed();
    }

    public static <T> Predicate<T> convert(javax.persistence.criteria.Predicate predicate) {
        if (ComparisonPredicate.class.isAssignableFrom(predicate.getClass())) {
            return convert((ComparisonPredicate) predicate);
        } else if (InPredicate.class.isAssignableFrom(predicate.getClass())) {
            return convert((InPredicate) predicate);
        } else if (CompoundPredicate.class.isAssignableFrom(predicate.getClass())) {
            return convert((CompoundPredicate) predicate);
        }
        throw new IllegalArgumentException(String.format("Can't handle Predicate %s.", predicate));
    }

    public static <T> Predicate<T> convert(ComparisonPredicate comparisonPredicate) {
        SingularAttributePath leftHandOperand = (SingularAttributePath) comparisonPredicate.getLeftHandOperand();
        ComparisonOperator operator = comparisonPredicate.getComparisonOperator(comparisonPredicate.isNegated());
        LiteralExpression rightHandOperand = (LiteralExpression) comparisonPredicate.getRightHandOperand();

        Predicate<T> predicate = f -> {
            Object o1 = getProperty(f, leftHandOperand.getAttribute().getName());
            Object o2 = rightHandOperand.getLiteral();
            return compare(o1, o2, operator);
        };

        return comparisonPredicate.isNegated() ? predicate.negate() : predicate;
    }

    public static <T> Predicate<T> convert(InPredicate inPredicate) {
        SingularAttributePath expression = (SingularAttributePath) inPredicate.getExpression();
        List<LiteralExpression> values = inPredicate.getValues();

        String fieldName = expression.getAttribute().getName();
        List<Object> list = values.stream().map(LiteralExpression::getLiteral).collect(Collectors.toList());

        Predicate<T> predicate = f -> list.contains(getProperty(f, fieldName));

        return inPredicate.isNegated() ? predicate.negate() : predicate;
    }

    public static <T> Predicate<T> convert(CompoundPredicate compoundpredicate) {
        List<javax.persistence.criteria.Predicate> expressions = compoundpredicate.getExpressions().stream()
                .map(e -> (javax.persistence.criteria.Predicate) e).collect(Collectors.toList());

        if (expressions.isEmpty()) {
            return f -> true;
        }

        if (expressions.size() == 1) {
            return convert(expressions.get(0));
        }

        BooleanOperator operator = compoundpredicate.getOperator();

        Predicate<T> predicate = BooleanOperator.AND.equals(operator)
                ? expressions.stream().map(JPAStreamConverter::<T>convert).reduce(Predicate::and).get()
                : expressions.stream().map(JPAStreamConverter::<T>convert).reduce(Predicate::or).get();

        return compoundpredicate.isNegated() ? predicate.negate() : predicate;
    }

    private static boolean compare(Object o1, Object o2, ComparisonOperator operator) {
        switch (operator) {
            case EQUAL:
                return Objects.equals(o1, o2);
            case NOT_EQUAL:
                return !Objects.equals(o1, o2);
            case LESS_THAN:
                return ((Comparable) o1).compareTo(o2) < 0;
            case LESS_THAN_OR_EQUAL:
                return ((Comparable) o1).compareTo(o2) <= 0;
            case GREATER_THAN:
                return ((Comparable) o1).compareTo(o2) > 0;
            case GREATER_THAN_OR_EQUAL:
                return ((Comparable) o1).compareTo(o2) >= 0;
        }
        return false;
    }

    private static Object getProperty(final Object bean, final String name) {
        try {
            return PropertyUtils.getProperty(bean, name);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException(String.format("Can't get property {} form {}.", name, bean));
        }
    }

}
