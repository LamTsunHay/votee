package com.votee.artifact.model;

import lombok.Data;

@Data
public class CharacterGuessResult {

    private int slot;
    private String guess;
    private String result;
}
