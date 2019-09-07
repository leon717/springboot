package com.leo.boot.jpa.spec;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.stream.Collectors;

import org.springframework.util.StringUtils;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchCriteria {

    private String key;
    private String operation;
    private Object value;

    public SearchCriteria(String key, String operation, Object value) {
        this.key = key;
        this.operation = operation;
        this.value = value;
    }

    public void format(Class<?> type) {
        if (value.getClass() != String.class) {
            return;
        }
        if ("$".equals(operation)) {
            this.value = Arrays.asList(value.toString().split("&")).stream().map(value -> doFormat(type, value))
                .collect(Collectors.toList());
        } else {
            this.value = doFormat(type, value.toString());
        }
    }

    private Object doFormat(Class<?> type, String value) {
        if (StringUtils.isEmpty(value)) {
            return value;
        }
        try {
            if (type.isEnum()) {
                Method method = type.getMethod("valueOf", String.class);
                return method.invoke(null, value);
            } else if (type == Date.class) {
                if (value.matches("[0-9]{8}")) {
                    return new SimpleDateFormat("yyyyMMdd").parse(value);
                } else if (value.matches("[0-9]{14}")) {
                    return new SimpleDateFormat("yyyyMMddHHmmss").parse(value);
                } else {
                    throw new RuntimeException("参数错误");
                }
            } else if (type == Boolean.class) {
                if ("true".equalsIgnoreCase(value)) {
                    return Boolean.TRUE;
                } else if ("false".equalsIgnoreCase(value)) {
                    return Boolean.FALSE;
                } else {
                    throw new RuntimeException("参数错误");
                }
            }
            return value;
        } catch (Exception e) {
            throw new RuntimeException("参数错误");
        }
    }

}
