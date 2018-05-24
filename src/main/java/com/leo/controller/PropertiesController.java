package com.leo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PropertiesController {

    @Value("${com.leo.title}")
    private String title;
    @Value("${com.leo.description}")
    private String description;
	
	@RequestMapping("/getProperties")
	public String index(){
		return title+description;
	}
}
