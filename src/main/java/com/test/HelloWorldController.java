package com.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@SpringBootApplication
public class HelloWorldController {
	
    @ResponseBody
    @RequestMapping(value = "/")
    String home() {    
        return "Hello World!";
    }
 
    public static void main(String[] args) throws Exception {
        SpringApplication.run(HelloWorldController.class, args);
    }

}
