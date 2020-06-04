package com.aoeii.leaderboard.ageofempirestwo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Getter
@Setter
@Component
@Entity
@Table(name = "playerdata")
@JsonIgnoreProperties(ignoreUnknown = true)
public class PlayerHistoryModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long U_ID;
    @JsonProperty("steam_id")
    private String STEAM_ID;
    @JsonProperty("name")
    private String NAME;
    @JsonProperty("country")
    private String COUNTRY;
    @JsonProperty("rating")
    private int RATING;
    @JsonProperty("won")
    private String WIN_STATEMENT;
    private String MATCH_UI;

}
