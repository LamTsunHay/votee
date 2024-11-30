package com.votee.artifact.controller;

import com.votee.artifact.service.GuessWordleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GuessWordleController {

    @Autowired
    GuessWordleService guessWordleService;

    @GetMapping("guess/random")
    public String guessRandomWordle(@RequestParam(name = "seed") String seed){
        return guessWordleService.guessRandom(seed);
    }
}
