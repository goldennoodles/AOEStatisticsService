package com.aoeii.leaderboard.ageofempirestwo.controller;

import com.aoeii.leaderboard.ageofempirestwo.model.MatchHistoryModel;
import com.aoeii.leaderboard.ageofempirestwo.model.PlayerHistoryModel;
import com.aoeii.leaderboard.ageofempirestwo.properties.ApplicationProperties;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;
import org.apache.coyote.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.View;

import javax.lang.model.type.ArrayType;
import java.lang.management.PlatformLoggingMXBean;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

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
        } catch (Exception ex) {
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
