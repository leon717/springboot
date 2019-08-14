package com.leo.boot.web.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.leo.boot.web.vo.ResultVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ResponseStatus(HttpStatus.OK)
@ResponseBody
@ControllerAdvice // 捕捉全局异常
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(BusinessException.class) // 捕捉指定Exception
	public ResultVO<?> handleBusinessException(BusinessException e) {
		log.error("BusinessException:", e);
		return new ResultVO<>(e.getCode(), e.getMessage());
	}

	@ExceptionHandler(Exception.class) // 捕捉指定Exception
	public ResultVO<?> handleException(Exception e) {
		log.error("Exception:", e);
		return new ResultVO<>("500", "Internal Server Error");
	}

}
