package com.leo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @RestController返回格式为json
 * @author Leonhardt
 *
 */
@RestController
public class HelloWorldController {
	
	@RequestMapping("/hello")
    public String index() {
        return "Hello World";
    }
}