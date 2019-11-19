package com.leo.boot.cache.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Accessors(chain = true, fluent = true)
@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
public class InfoExtention extends Info {

    private String extention;
}
