package com.example.demo.notification;


import com.example.demo.exception.FilterException;

import com.example.demo.repository.MessageRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigInteger;


import static com.example.demo.models.Type.*;
import static org.junit.jupiter.api.Assertions.assertThrows;


@SpringBootTest
public class NotificationServiceTest {

    @Autowired
    private NotificationServiceImpl service;

    @Autowired
    private MessageRepository messageRepository;

    private final BigInteger USER_1 = BigInteger.ONE;

    @BeforeEach
    public void setUp() {
        messageRepository.deleteAll();
    }


    @Test
    public void testingStatusUpdateRule() throws FilterException {
        service.send(STATUS_UPDATE, USER_1, "status 1");

        service.send(STATUS_UPDATE, USER_1, "status 2");

        assertThrows(FilterException.class, () ->
                service.send(STATUS_UPDATE, USER_1, "status 3"));

    }

    @Test
    public void testingDailyNewsRule() throws FilterException {
        service.send(DAILY_NEWS, USER_1, "news 1");

        assertThrows(FilterException.class, () ->
                service.send(DAILY_NEWS, USER_1, "news 2"));
    }

    @Test
    public void testingMarketingRule() throws FilterException {
        service.send(MARKETING, USER_1, "marketing 1");

        service.send(MARKETING, USER_1, "marketing 2");

        service.send(MARKETING, USER_1, "marketing 3");

        assertThrows(FilterException.class, () ->
                service.send(MARKETING, USER_1, "marketing 4"));
    }

    @Test
    public void testingProjectInvitationsRule() throws FilterException {
        service.send(PROJECT_INVITATIONS, USER_1, "project 1");

        assertThrows(FilterException.class, () ->
                service.send(PROJECT_INVITATIONS, USER_1, "project 2"));
    }


    @Test
    public void testingProjectInvitationsRuleWaiting10Seconds() throws FilterException, InterruptedException {
        service.send(PROJECT_INVITATIONS, USER_1, "project 1");

        assertThrows(FilterException.class, () ->
                service.send(PROJECT_INVITATIONS, USER_1, "project 2"));

        Thread.sleep(10 * 1000);

        service.send(PROJECT_INVITATIONS, USER_1, "news 3");
    }

}
