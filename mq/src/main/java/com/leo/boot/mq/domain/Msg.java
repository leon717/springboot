package com.leo.boot.mq.domain;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
public class Msg<T> implements Serializable{
	private static final long serialVersionUID = 1L;
	private String code;
	private String msg;
	private List<T> data;
}
