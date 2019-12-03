package com.leo.boot.aop.annotation.domain;

import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.Getter;

@Getter
@Setter
@Accessors(chain = true)
public class User {

    private String id;
    private String name;
}
