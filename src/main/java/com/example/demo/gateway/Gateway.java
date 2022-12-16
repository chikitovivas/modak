package com.example.demo.gateway;

import org.springframework.stereotype.Component;

import java.math.BigInteger;

@Component
public class Gateway {
    /* already implemented */

    public void send(BigInteger userId, String message) {

        System.out.println("sending message to user " + userId);

    }
}
