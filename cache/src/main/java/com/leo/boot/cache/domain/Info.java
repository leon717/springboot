package com.leo.boot.cache.domain;

import lombok.Data;
import lombok.experimental.Accessors;

@Accessors(chain = true, fluent = true)
@Data
public class Info {
	private String id;
	private String name;
}
