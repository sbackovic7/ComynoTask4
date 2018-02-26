package com.comyno.counter.letter.service;

import com.comyno.counter.letter.CounterApplication;
import com.comyno.counter.letter.util.Util;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = CounterApplication.class)
public class CounterServiceTests {

    @Value("${target.letter}")
    private char targetLetter;

    @Autowired
    private CounterService charCounterService;


    @Test(expected = NullPointerException.class)
    public void exceptionTest() {
        charCounterService.count(null);
    }

    @Test
    public void emptyInputTest() {
        assertEquals(0, charCounterService.count(""));
    }

    @Test
    public void nonEmptyInputTest() {
        assertEquals(1, charCounterService.count(Util.generateTextInput(targetLetter, 1)));
        assertEquals(10, charCounterService.count(Util.generateTextInput(targetLetter, 10)));
        assertEquals(10000000, charCounterService.count(Util.generateTextInput(targetLetter, 10000000)));
    }

}
