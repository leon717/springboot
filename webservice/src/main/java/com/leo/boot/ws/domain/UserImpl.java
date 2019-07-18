package com.leo.boot.ws.domain;

import javax.jws.WebService;

import org.springframework.stereotype.Service;

@Service("user")
@WebService
public class UserImpl implements User {

    @Override
    public String say() {
        return "hello world!";
    }

}
