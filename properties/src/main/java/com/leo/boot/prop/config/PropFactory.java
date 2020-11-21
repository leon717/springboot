package com.leo.boot.prop.config;

import com.leo.boot.prop.domain.Prop;
import org.springframework.beans.factory.FactoryBean;

public class PropFactory implements FactoryBean<Prop> {

    private String name;

    private String value;

    @Override
    public Prop getObject() throws Exception {
        return new Prop(name, value);
    }

    @Override
    public Class<?> getObjectType() {
        return Prop.class;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
