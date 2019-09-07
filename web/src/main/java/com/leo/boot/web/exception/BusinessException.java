package com.leo.boot.web.exception;

import com.leo.boot.web.status.Status;

import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	private String code;

    public BusinessException(String code, String message) {
        super(message);
        this.code = code;
    }
    
    public BusinessException(Status status) {
        super(status.getMsg());
        this.code = status.getCode();
    }
    
}
