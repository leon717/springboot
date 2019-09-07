package com.leo.boot.prop.config;

import java.util.List;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import com.leo.boot.prop.domain.Prop;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix="prop")
public class PropConfig {

    private String single;
    
    private String[] array;
    
    private List<String> list;
    
    private Map<String, String> map;
    
    private List<Map<String, String>> mapList;
    
    private Map<String, List<String>> listMap;
    
    private Prop prop;
    
}
