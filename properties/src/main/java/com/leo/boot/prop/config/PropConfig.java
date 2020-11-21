package com.leo.boot.prop.config;

import com.leo.boot.prop.domain.Prop;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "prop")
public class PropConfig {

    private String single;

    private String[] array;

    private List<String> list;

    private Map<String, String> map;

    private List<Map<String, String>> mapList;

    private Map<String, List<String>> listMap;

    private Prop prop;

    @ConfigurationProperties(prefix = "prop.prop1")
    @Bean
    public PropFactory prop1() {
        return new PropFactory();
    }

    @ConfigurationProperties(prefix = "prop.prop2")
    @Bean
    public PropFactory prop2() {
        return new PropFactory();
    }

}
