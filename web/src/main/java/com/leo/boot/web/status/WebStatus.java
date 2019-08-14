package com.leo.boot.web.status;

import lombok.Getter;

@Getter
public enum WebStatus implements Status {

	FORBIDDEN("403", "Forbidden");

	private String code;

	private String msg;

	WebStatus(String code, String msg) {
		this.code = code;
		this.msg = msg;
	}

}
