package com.aoeii.leaderboard.ageofempirestwo.controller;

import com.aoeii.leaderboard.ageofempirestwo.model.MatchHistoryModel;
import com.aoeii.leaderboard.ageofempirestwo.model.PlayerHistoryModel;
import com.aoeii.leaderboard.ageofempirestwo.properties.ApplicationProperties;
import com.aoeii.leaderboard.ageofempirestwo.repo.AOERepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;
import lombok.var;
import net.minidev.json.JSONArray;
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

import java.util.ArrayList;
import java.util.Arrays;
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

    @Autowired
    AOERepository aoeRepository;

    /**
     * Get the Player Data from AOE2:DE servers
     * @param user - Input from @GetMapping
     * @return ResponseEntity<Object>
     * @throws JsonProcessingException JPE
     */
    @GetMapping("/get-data")
    public ResponseEntity<Object> GetAOEData (@RequestParam String user) throws JsonProcessingException {
        try {
            // Assign the user
            properties.setAOE_USER(user);
            log.info("User is now: " + properties.getAOE_USER());

            //Local Method String URL holder.
            String URI = properties.getPLAYER_MATCH_HISTORY() +
                    properties.getAOE_USER() +
                    properties.getMATCH_COUNT();
            log.info(URI);

            //Load up the string from the AOE2:DE servers based on 'USER'.
            ResponseEntity<String> loadedString = restTemplate.getForEntity(URI, String.class);

            //Parse the String
            Object parsedResults = Configuration.defaultConfiguration().jsonProvider().parse(loadedString.getBody());

            //Read the Json and Filter
            List<JSONArray> playerStats = JsonPath.read(parsedResults, "$..players");
            List<MatchHistoryModel> matchHistoryModels = objectMapper.readValue(loadedString.getBody(), new TypeReference<List<MatchHistoryModel>>(){});

            //Create LocalLists to hold accessible Model Data.
            List<PlayerHistoryModel> playerHistoryModelList = new ArrayList<>();
            List<MatchHistoryModel> matchHistoryModelList = new ArrayList<>();

            // Do the mapping and adding to LocalLists.
            for (JSONArray model : playerStats) {
                PlayerHistoryModel[] phm = objectMapper.readValue(model.toJSONString(), PlayerHistoryModel[].class);
                playerHistoryModelList.addAll(Arrays.asList(phm));
            }
            for (MatchHistoryModel model : matchHistoryModels){
                matchHistoryModelList.addAll(Arrays.asList(model));
            }

            //Iterate through the list and save to database.
            for (PlayerHistoryModel mod : playerHistoryModelList){
                aoeRepository.save(mod);
            }

            // Just Return some data for now, but soone will go into database and querying will start.
            var toReturn = playerHistoryModelList.get(0).getNAME() +
                    "\n" + matchHistoryModelList.get(0).getSERVER() +
                    "\n" + playerHistoryModelList.get(0).getRATING() +
                    "\n" + playerHistoryModelList.get(0).getWIN_LOSS_STATEMENT() +
                    "\n" + playerHistoryModelList.get(1).getNAME() +
                    "\n" + matchHistoryModelList.get(1).getSERVER() +
                    "\n" + playerHistoryModelList.get(1).getRATING() +
                    "\n" + playerHistoryModelList.get(1).getWIN_LOSS_STATEMENT();

            //Return output back up the the user with an OK statement!
            return new ResponseEntity<>(toReturn, HttpStatus.OK);

            //I AM A TEAPOT....
        } catch (HttpClientErrorException ex) {
            return new ResponseEntity<>(ex, HttpStatus.I_AM_A_TEAPOT);
        }
    }

    /**
     * Ignore the stuff below for now, may end up scrapping it.
     * @param user
     * @return
     */
    @GetMapping("/player-ranking")
    public ResponseEntity<Object> GetPlayerRankingHistory (@RequestParam String user) {
        properties.setAOE_USER(user);
        ResponseEntity<String> loadedString = restTemplate.getForEntity(
                properties.getPLAYER_RANKING_HISTORY() +
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

    @GetMapping("/api")
    public ResponseEntity<Object> ReturnAPIInformationToUser () {
        ResponseEntity<String> loadedString = restTemplate.getForEntity(properties.getRETURN_ALL_LOBBIES(), String.class);
        return new ResponseEntity<>(loadedString, HttpStatus.OK);
    }
}
