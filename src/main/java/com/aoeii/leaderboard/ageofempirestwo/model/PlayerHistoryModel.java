package com.aoeii.leaderboard.ageofempirestwo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@JsonIgnoreProperties(ignoreUnknown = true)
public class PlayerHistoryModel {
    @JsonProperty("steam_id")
    private String STEAM_ID;
    @JsonProperty("name")
    private String NAME;
    @JsonProperty("country")
    private String COUNTRY;
    @JsonProperty("rating")
    private int RATING;
    @JsonProperty("won")
    private String WIN_LOSS_STATEMENT;

}
