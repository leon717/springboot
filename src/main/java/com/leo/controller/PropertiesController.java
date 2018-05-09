package com.leo.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.leo.properties.LeoProperties;

@RestController
public class PropertiesController {

	@Resource
	private LeoProperties leoProperties;
	
	@RequestMapping("/getProperties")
	public LeoProperties index(){
		return leoProperties;
	}
}
