package com.leo.boot.webflux.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

@RunWith(SpringRunner.class)
@WebFluxTest
public class HelloControllerTest {

    @Autowired
    private WebTestClient client;

    @Test
    public void testHello() {
        client.get().uri("/hello").exchange().expectStatus().isOk();
    }

}
