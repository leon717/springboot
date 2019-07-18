package com.leo.boot.ws.domain;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface User {

    @WebMethod
    String say();
}
