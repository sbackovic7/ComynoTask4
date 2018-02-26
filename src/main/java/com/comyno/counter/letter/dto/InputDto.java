package com.comyno.counter.letter.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;


@Data
public class InputDto {

    @NotNull
    String text;

}
