package com.aoeii.leaderboard.ageofempirestwo.controller;

import com.aoeii.leaderboard.ageofempirestwo.model.PlayerHistoryModel;
import com.aoeii.leaderboard.ageofempirestwo.properties.ApplicationProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;
import org.json.JSONObject;
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

/*
    Quick Note: Mapping doesn't currently work and dont have the brain power to figure out why...
    TODO: Drink lesss G&T;s.
 */

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

    @GetMapping("/get-data")
    public ResponseEntity<Object> GetAOEData (@RequestParam String user) {
        try {
            properties.setAOE_USER(user);
            log.info("User is now: " + properties.getAOE_USER());

            ResponseEntity<String> loadedString = restTemplate.getForEntity(
                    properties.PLAYER_MATCH_HISTORY +
                            properties.getAOE_USER() +
                            properties.getMATCH_COUNT(), String.class);

            Object parsedResults = Configuration.defaultConfiguration().jsonProvider().parse(loadedString.getBody());
            List<PlayerHistoryModel> PlayerStats = JsonPath.read(parsedResults, "$..players");

            return new ResponseEntity<>(PlayerStats, HttpStatus.OK);

        } catch (HttpClientErrorException ex) {
            return new ResponseEntity<>(ex, HttpStatus.I_AM_A_TEAPOT);
        }
    }

    @GetMapping("/player-ranking")
    public ResponseEntity<Object> GetPlayerRankingHistory (@RequestParam String user) {
        properties.setAOE_USER(user);
        ResponseEntity<String> loadedString = restTemplate.getForEntity(
                properties.PLAYER_RANKING_HISTORY +
                        properties.getAOE_USER() +
                        properties.getMATCH_COUNT(), String.class);

        Object parsedResults = Configuration.defaultConfiguration().jsonProvider().parse(loadedString.getBody());
        List<String> playerRanking = JsonPath.read(parsedResults, "$..*");

        return new ResponseEntity<>(playerRanking, HttpStatus.OK);
    }

    /*
        This doesn't work correctly , but will probs fix later.. Not entirely sure why they're returning all users
        with different counts.... Weird
     */
    @GetMapping("/player-count")
    public ResponseEntity<Object> PlayersCurrentlyOnline () {
        ResponseEntity<String> loadedString = restTemplate.getForEntity(properties.getPLAYER_ONLINE_GAME_COUNT(), String.class);

        Object parsedResults = Configuration.defaultConfiguration().jsonProvider().parse(loadedString.getBody());
        List<String> results = JsonPath.parse(parsedResults).read("$..num_players");

        return new ResponseEntity<>(results, HttpStatus.OK);
    }
}
