package com.leo.config;

import java.lang.reflect.Method;
import java.util.Date;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.ConvertUtilsBean;
import org.apache.commons.beanutils.Converter;
import org.apache.commons.beanutils.converters.DateConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.leo.domain.User.Gender;

@Configuration
public class BeanUtilsConfig {
	
	@Bean
	public BeanUtilsBean getBeanUtilsBean(){
		ConvertUtilsBean convertUtils = new ConvertUtilsBean();
		Converter enumConverter = enumConverter();
		//注册Converter
		convertUtils.register(dateConverter(), Date.class);
		convertUtils.register(enumConverter, Gender.class);
		
        return new BeanUtilsBean(convertUtils);
	}
	
	public Converter dateConverter() {
		DateConverter dateConverter = new DateConverter();
		dateConverter.setPatterns(new String[]{"yyyy-MM-dd","yyyy-MM-dd HH:mm:ss"});
		return dateConverter;
	}
    
    public Converter enumConverter() {
		return new Converter() {
			
			@SuppressWarnings({ "unchecked", "rawtypes" })
			@Override
			public Object convert(Class type, Object value) {   
                if(value instanceof Enum){
                	return value;
                }
                
                //enum为ordinal或String类型
                if(value != null && value instanceof Integer){
                	int ordinal =  (int) value;
	                try {
						Method method = type.getMethod("values");
						Object[] enums = (Object[]) method.invoke(null);
						return enums[ordinal];
					} catch (Exception e) {
						e.printStackTrace();
					}
                }else if(value != null && value instanceof String){
                	String name =  (String) value;
	                try {
						Method method = type.getMethod("valueOf", String.class);
						Object enumObj = method.invoke(null, name);
						return enumObj;
					} catch (Exception e) {
						e.printStackTrace();
					}
                }
                
                return null;
            }  
		};
    }
}
