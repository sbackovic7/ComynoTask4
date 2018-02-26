package com.comyno.counter.letter.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
public class CounterService {

    @Value("${target.letter}")
    private char targetLetter;

    public long count(String text) {
        return text.chars().filter(ch -> ch == targetLetter).count();
    }

}
