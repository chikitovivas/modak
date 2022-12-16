package com.example.demo.notification;

import com.example.demo.exception.FilterException;
import com.example.demo.models.Type;

import java.math.BigInteger;

public interface NotificationService {

    void send(Type type, BigInteger userId, String message) throws FilterException;

}
