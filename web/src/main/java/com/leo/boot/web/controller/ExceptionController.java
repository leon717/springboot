package com.leo.boot.web.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.leo.boot.web.exception.BusinessException;
import com.leo.boot.web.status.WebStatus;

import io.swagger.annotations.Api;

@Api(tags = "异常测试")
@RestController
@RequestMapping(value = "/exception", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ExceptionController {

    @GetMapping("/runtime")
    public String runtimeException() {
        throw new RuntimeException();
    }

    @GetMapping("/business")
    public String businessException() {
        throw new BusinessException(WebStatus.FORBIDDEN);
    }

}