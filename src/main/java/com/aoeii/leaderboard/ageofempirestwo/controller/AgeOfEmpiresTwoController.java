package com.aoeii.leaderboard.ageofempirestwo.controller;

import com.aoeii.leaderboard.ageofempirestwo.properties.ApplicationProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import java.util.List;


@Component
@RestController
public class AgeOfEmpiresTwoController {

    private static final Logger log = LoggerFactory.getLogger(AgeOfEmpiresTwoController.class);

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    ApplicationProperties properties;

    @Autowired
    ObjectMapper objectMapper;

    @GetMapping("/getData")
    public String getAOEData () {
        try {
            ResponseEntity<String> loadedString = restTemplate.getForEntity(properties.PLAYER_MATCH_HISTORY, String.class);
            Object parsedResults = Configuration.defaultConfiguration().jsonProvider().parse(loadedString.getBody());

            List<String> PlayerStats = JsonPath.read(parsedResults, "$..players");
            List<String> MatchStats = JsonPath.read(parsedResults, "$..*");

            return "//======= Player Stats =========" + "\n"
                    + PlayerStats +
                    "//======= Match Stats ==========" + "\n"
                    + MatchStats + "\n";

        } catch (HttpClientErrorException ex) {
            return ex.getMessage();
        }
    }

    @GetMapping("/addUser")
    public String addAOEUser (@RequestParam String user) {
        return null;
    }

    @GetMapping("/clearusers")
    public ResponseEntity<String> clearUsers () {
        return new ResponseEntity<String>("OK", HttpStatus.OK);
    }
}
