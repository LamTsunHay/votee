package com.votee.artifact.feignClient;

import com.votee.artifact.model.CharacterGuessResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "voteeClient", url = "${feign.url}")
public interface VoteeSourceClient {

    @GetMapping("random")
    public List<CharacterGuessResult> guessRandom(@RequestParam(name = "guess") String guess, @RequestParam(name = "seed") String seed);
}
