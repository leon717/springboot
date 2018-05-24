package com.leo.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.leo.exception.IndexException;
import com.leo.vo.ResultVO;

@ControllerAdvice	//捕捉全局异常
@ResponseBody		//重新定义response
public class CommonExceptionHandler {

    @ExceptionHandler(Exception.class)	//捕捉指定Exception
    public String handleException(Exception e) {
        return "Exception";
    }
    
    @SuppressWarnings("rawtypes")
	@ExceptionHandler(IndexException.class)	//捕捉指定Exception
    @ResponseStatus(HttpStatus.FORBIDDEN)	//指定状态码status
    public ResultVO handleIndexException(IndexException e) {
        return new ResultVO(e.getCode(), e.getMessage());
    }
}
