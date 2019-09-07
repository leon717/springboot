package com.leo.boot.ws.domain;

import org.springframework.stereotype.Service;

@Service("user")
public class UserImpl implements User {

    @Override
    public String say() {
        return "hello world!";
    }

}
