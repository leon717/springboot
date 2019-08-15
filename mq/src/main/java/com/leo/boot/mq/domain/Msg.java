package com.leo.boot.mq.domain;

import java.util.List;

import lombok.Data;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
public class Msg<T> {
    private String code;
    private String msg;
    private List<T> data;
}
