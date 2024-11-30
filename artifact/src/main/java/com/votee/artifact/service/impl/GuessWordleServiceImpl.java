package com.votee.artifact.service.impl;

import com.votee.artifact.feignClient.VoteeSourceClient;
import com.votee.artifact.model.CharacterGuessResult;
import com.votee.artifact.service.GuessWordleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GuessWordleServiceImpl implements GuessWordleService {

    @Autowired
    VoteeSourceClient voteeSourceClient;

    public String guessRandom(String seed){
        char[] ans = {'*','*','*','*','*'};
        List<Character> existChars = new ArrayList<>();

//        {t, h, e, f}
        int wordCount = 0;
        char[] guessWords = {'*','*','*','*','*'};
        for(int i=0;i<26;i++){
             if(wordCount < 5){
                guessWords[wordCount++] = (char) (97 + i);
                if(i == 25){
                    String guessString = new String(guessWords);
                    CharacterGuessResult characterResult = voteeSourceClient.guessRandom(guessString, seed).get(0);
                    if(characterResult.equals("correct")){
                        ans[characterResult.getSlot()] = characterResult.getGuess().charAt(0);
                        existChars.add(characterResult.getGuess().charAt(0));
                    }else if(characterResult.equals("present")){
                        existChars.add(characterResult.getGuess().charAt(0));
                    }
                }
            } else{
                String guessString = new String(guessWords);
                List<CharacterGuessResult> characterGuessResults = voteeSourceClient.guessRandom(guessString, seed);
                characterGuessResults.forEach( characterGuess ->{
                    if(characterGuess.getResult().equals("correct")){
                        ans[characterGuess.getSlot()] = characterGuess.getGuess().charAt(0);
                        existChars.add(characterGuess.getGuess().charAt(0));
                    }else if(characterGuess.getResult().equals("present")){
                        existChars.add(characterGuess.getGuess().charAt(0));
                    }
                });
                wordCount = 0;
                i--;
            }
        }


        for(char c: existChars){
            StringBuilder guessWordBuilder = new StringBuilder();
            for(int i=0;i < 5; i++){
                guessWordBuilder.append(c);
            }
            List<CharacterGuessResult> characterGuessResults = voteeSourceClient.guessRandom(guessWordBuilder.toString(), seed);
            characterGuessResults.forEach(characterGuess -> {
                String result = characterGuess.getResult();
                if(result.equals("correct")){
                    ans[characterGuess.getSlot()] = characterGuess.getGuess().charAt(0);
                }
            });
        }

        return new String(ans);
    }

}