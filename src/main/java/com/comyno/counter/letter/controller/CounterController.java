package com.comyno.counter.letter.controller;

import com.comyno.counter.letter.dto.InputDto;
import com.comyno.counter.letter.error.ErrorMessage;
import com.comyno.counter.letter.service.CounterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
public class CounterController {

    private final CounterService counterService;


    @Autowired
    public CounterController(CounterService counterService) {
        this.counterService = counterService;
    }

    @RequestMapping(path = "/letter_counter", method = RequestMethod.POST)
    public ResponseEntity counter(@Valid @RequestBody InputDto inputData, BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity<>(ErrorMessage.IRREGULAR_INPUT_FORMAT_ERROR, HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(counterService.count(inputData.getText()), HttpStatus.OK);
        }
    }

}
