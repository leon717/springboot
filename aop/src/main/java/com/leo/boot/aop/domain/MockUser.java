package com.leo.boot.aop.domain;

import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.Getter;

@Getter
@Setter
@Accessors(chain = true)
public class MockUser {

    private String id;
    private String name;
}
