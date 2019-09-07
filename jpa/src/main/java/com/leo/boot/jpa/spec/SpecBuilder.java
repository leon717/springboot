package com.leo.boot.jpa.spec;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.data.jpa.domain.Specification;

public class SpecBuilder<T> {

    private final List<SearchCriteria> params;

    public SpecBuilder() {
        params = new ArrayList<>();
    }

    public SpecBuilder(String search) {
        params = new ArrayList<>();
        Pattern pattern = Pattern.compile("(\\w+)([:<>=$])([_&\\w\\u4e00-\\u9fa5&]+),");
        Matcher matcher = pattern.matcher(search + ",");
        while (matcher.find()) {
            with(matcher.group(1), matcher.group(2), matcher.group(3));
        }
    }
    
    public SpecBuilder<T> with(SearchCriteria searchCriteria) {
        params.add(searchCriteria);
        return this;
    }

    public SpecBuilder<T> with(String key, String operation, Object value) {
        params.add(new SearchCriteria(key, operation, value));
        return this;
    }

    public Specification<T> build() {
        if (params.size() == 0) {
            return null;
        }

        Specification<T> result = null;
        for (SearchCriteria param : params) {
            result = Specification.where(result).and(new Spec<T>(param));
        }
        return result;
    }
    
    public Specification<T> orBuild() {
        if (params.size() == 0) {
            return null;
        }

        Specification<T> result = null;
        for (SearchCriteria param : params) {
            result = Specification.where(result).or(new Spec<T>(param));
        }
        return result;
    }
    
}
