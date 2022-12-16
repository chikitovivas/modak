package com.example.demo.notification;

import com.example.demo.exception.FilterException;
import com.example.demo.gateway.Gateway;
import com.example.demo.models.Message;
import com.example.demo.models.Rule;
import com.example.demo.models.Type;
import com.example.demo.repository.MessageRepository;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class NotificationServiceImpl implements NotificationService {

    private final Gateway gateway;
    private final MessageRepository messageRepository;
    private final Map<Type, Rule> rules;

    public NotificationServiceImpl(final Gateway gateway,
                                   final Map<Type, Rule> rules,
                                   final MessageRepository messageRepository) {
        this.gateway = gateway;
        this.rules = rules;
        this.messageRepository = messageRepository;
    }

    // TASK: IMPLEMENT this

    @Override
    public void send(Type type, BigInteger userId, String message) throws FilterException {
        filterMessage(type, userId);
        gateway.send(userId, message);
        messageRepository.save(buildMessageEntity(type, userId, message));
    }

    private void filterMessage(Type type, BigInteger userId) throws FilterException {
        Rule rule = rules.get(type);
        if (Objects.isNull(rule)) {
            System.out.println("WARNING - TYPE DOES NOT HAVE RULE ASSIGNED!");
            return;
        }
        LocalDateTime since = LocalDateTime.now().minus(rule.getTimeLimit(), rule.getTypeTimeLimit());
        List<Message> messages = messageRepository
                .findAllByUserIdAndTypeAndTimeSentIsGreaterThanEqual(userId, type, since);
        if (messages.size() >= rule.getLimit()) {
            throw new FilterException(
                    String.format("Cannot send more %s type messages, limit reached %s", type, rule.getLimit())
            );
        }
    }

    private Message buildMessageEntity(Type type, BigInteger userId, String message) {
        return Message.builder()
                .message(message)
                .type(type)
                .userId(userId)
                .timeSent(LocalDateTime.now())
                .build();
    }
}