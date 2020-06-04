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
    public String STEAM_ID;
    @JsonProperty("name")
    public String NAME;
    @JsonProperty("country")
    public String COUNTRY;
    @JsonProperty("rating")
    public int RATING;
    @JsonProperty("won")
    public String WIN_LOSS_STATEMENT;

}
