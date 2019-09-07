package com.leo.boot.web.exception;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.leo.boot.web.vo.ResultVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ResponseStatus(HttpStatus.OK)
@ResponseBody
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
        HttpStatus status, WebRequest request) {
        log.error("Invalid request of : {}", request.toString(), ex);
        return new ResponseEntity<>(new ResultVO<>("400", "Bad Request").addData(ex.getMessage()), HttpStatus.OK);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
        HttpHeaders headers, HttpStatus status, WebRequest request) {
        log.error("Invalid request of : {}", request.toString(), ex);
        List<Object> result = ex.getBindingResult().getAllErrors().stream().map(ObjectError::getDefaultMessage)
            .collect(Collectors.toList());
        return new ResponseEntity<>(new ResultVO<>("400", "Bad Request").setData(result), HttpStatus.OK);
    }

    @Override
    protected ResponseEntity<Object> handleBindException(BindException ex, HttpHeaders headers, HttpStatus status,
        WebRequest request) {
        log.error("Invalid request of : {}", request.toString(), ex);
        List<Object> result = ex.getBindingResult().getAllErrors().stream().map(ObjectError::getDefaultMessage)
            .collect(Collectors.toList());
        return new ResponseEntity<>(new ResultVO<>("400", "Bad Request").setData(result), HttpStatus.OK);
    }

    @ExceptionHandler(BusinessException.class)
    public ResultVO<?> handleBusinessException(BusinessException e) {
        log.error("BusinessException:", e);
        return new ResultVO<>(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResultVO<?> handleException(Exception e) {
        log.error("Exception:", e);
        return new ResultVO<>("500", "Internal Server Error");
    }

}
