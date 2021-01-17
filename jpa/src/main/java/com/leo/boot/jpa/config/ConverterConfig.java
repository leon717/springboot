package com.leo.boot.jpa.config;

import com.google.common.base.CaseFormat;
import com.google.common.base.Converter;
import com.leo.boot.jpa.domain.projection.UserVO;
import org.springframework.cglib.beans.BeanMap;
import org.springframework.cglib.core.ReflectUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.GenericConverter;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.core.convert.support.GenericConversionService;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Configuration
public class ConverterConfig {

    private Converter<String, String> caseConverter = CaseFormat.UPPER_UNDERSCORE.converterTo(CaseFormat.LOWER_CAMEL);

    private GenericConversionService genericConversionService = ((GenericConversionService) DefaultConversionService.getSharedInstance());

    @PostConstruct
    public void registry() {
        genericConversionService.addConverter(projectionConverter());
    }

    public GenericConverter projectionConverter() {
        return new GenericConverter() {

            @Override
            public Set<ConvertiblePair> getConvertibleTypes() {
                return Stream.of(new ConvertiblePair(Map.class, UserVO.class)).collect(Collectors.toSet());
            }

            @Override
            public Object convert(Object source, TypeDescriptor sourceType, TypeDescriptor targetType) {
                Object target = ReflectUtils.newInstance(targetType.getType());
                BeanMap beanMap = BeanMap.create(target);

                ((Map<String, Object>) source).forEach((key, value) -> {
                    String propertyName = caseConverter.convert(key);
                    if (!beanMap.containsKey(propertyName) || Objects.isNull(key)) {
                        return;
                    }
                    Object propertyValue = genericConversionService.convert(value, beanMap.getPropertyType(propertyName));
                    beanMap.put(propertyName, propertyValue);
                });
                return target;
            }
        };
    }
}
