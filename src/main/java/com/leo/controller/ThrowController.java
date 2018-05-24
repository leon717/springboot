package com.leo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.leo.exception.IndexException;

@RestController
public class ThrowController {
	
	@RequestMapping("/throw")
    public String common() {
		throw new RuntimeException("exception");
    }
	
	@RequestMapping("/throw/index")
    public String index() {
		throw new IndexException("403", "index");
    }
}