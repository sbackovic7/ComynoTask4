package com.comyno.counter.letter.controller;

import com.comyno.counter.letter.CounterApplication;
import com.comyno.counter.letter.dto.InputDto;
import com.comyno.counter.letter.error.ErrorMessage;
import com.comyno.counter.letter.util.Util;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = CounterApplication.class)
@AutoConfigureMockMvc
public class CounterControllerTests {

    @Value("${target.letter}")
    private char targetLetter;

    @Autowired
    private MockMvc mockMvc;


    @Test
    public void irregularInputTest() throws Exception {
        String result = sendRequest(new InputDto(), status().isBadRequest());
        assertEquals(ErrorMessage.IRREGULAR_INPUT_FORMAT_ERROR, result);
    }

    @Test
    public void regularEmptyInputTest() throws Exception {
        InputDto inputDto = new InputDto();
        inputDto.setText("");
        String result = sendRequest(inputDto, status().isOk());
        assertTrue(Integer.valueOf(result).equals(0));
    }

    @Test
    public void regularNonEmptyInputTest() throws Exception {
        InputDto inputDto = new InputDto();
        inputDto.setText(Util.generateTextInput(targetLetter, 100));
        String result = sendRequest(inputDto, status().isOk());
        assertTrue(Integer.valueOf(result).equals(100));
    }

    private String sendRequest(InputDto inputDto, ResultMatcher resultMatcher) throws Exception {
        String inputDtoJson = new ObjectMapper().writeValueAsString(inputDto);
        return mockMvc.perform(post("/letter_counter")
                .contentType(MediaType.APPLICATION_JSON)
                .content(inputDtoJson))
                .andExpect(resultMatcher)
                .andReturn()
                .getResponse()
                .getContentAsString();
    }

}
