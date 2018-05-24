package com.leo.exception;

public class IndexException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	private String code;

    public IndexException(String code, String message) {
        super(message);
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
