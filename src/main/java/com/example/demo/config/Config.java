package com.example.demo.config;

import com.example.demo.models.Rule;
import com.example.demo.models.Type;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import java.time.temporal.ChronoUnit;
import java.util.Map;

import static com.example.demo.models.Type.MARKETING;
import static com.example.demo.models.Type.STATUS_UPDATE;
import static com.example.demo.models.Type.DAILY_NEWS;
import static com.example.demo.models.Type.PROJECT_INVITATIONS;

@Configuration
public class Config {

    @Bean
    public Map<Type, Rule> rules() {
        return Map.of(
                STATUS_UPDATE, Rule.builder().type(STATUS_UPDATE).timeLimit(1).typeTimeLimit(ChronoUnit.MINUTES).limit(2).build(),
                DAILY_NEWS, Rule.builder().type(DAILY_NEWS).timeLimit(1).typeTimeLimit(ChronoUnit.DAYS).limit(1).build(),
                PROJECT_INVITATIONS, Rule.builder().type(PROJECT_INVITATIONS).timeLimit(10).typeTimeLimit(ChronoUnit.SECONDS).limit(1).build(),
                MARKETING, Rule.builder().type(MARKETING).timeLimit(1).typeTimeLimit(ChronoUnit.HOURS).limit(3).build()
        );
    }
}
