package com.leo.updown;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.spring4all.swagger.EnableSwagger2Doc;

@EnableSwagger2Doc
@SpringBootApplication
public class UpdownApplication {

	public static void main(String[] args) {
		SpringApplication.run(UpdownApplication.class, args);
	}
}
