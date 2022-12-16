package com.example.demo.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.temporal.ChronoUnit;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Rule {
    private Type type;
    private long timeLimit;
    private ChronoUnit typeTimeLimit;
    private long limit;
}
