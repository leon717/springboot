package com.leo.boot.jpa.config;

import com.google.common.base.CaseFormat;
import com.google.common.base.Converter;
import com.leo.boot.jpa.domain.projection.UserVO;
import org.springframework.cglib.beans.BeanMap;
import org.springframework.cglib.core.ReflectUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.GenericConverter;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.core.convert.support.GenericConversionService;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Configuration
public class ConverterConfig {

    private Converter<String, String> caseConverter = CaseFormat.UPPER_UNDERSCORE.converterTo(CaseFormat.LOWER_CAMEL);

    @PostConstruct
    public void registry() {
        GenericConversionService genericConversionService = ((GenericConversionService) DefaultConversionService.getSharedInstance());
        genericConversionService.addConverter(dbProjectionConverter());
    }

    @Bean
    public GenericConverter dbProjectionConverter() {
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
                    if (!beanMap.containsKey(propertyName)) {
                        return;
                    }

                    Class propertyType = beanMap.getPropertyType(propertyName);
                    if (Enum.class.isAssignableFrom(propertyType) && !StringUtils.isEmpty(value.toString())) {
                        beanMap.put(propertyName, Enum.valueOf(propertyType, value.toString()));
                    } else {
                        beanMap.put(propertyName, value);
                    }
                });
                return target;
            }
        };
    }
}
