package com.example.demo.repository;


import com.example.demo.models.Message;
import com.example.demo.models.Type;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;


@Repository
public interface MessageRepository extends CrudRepository<Message, BigInteger> {
    List<Message> findAllByUserIdAndTypeAndTimeSentIsGreaterThanEqual(
            BigInteger userId, Type type, LocalDateTime since);
}

