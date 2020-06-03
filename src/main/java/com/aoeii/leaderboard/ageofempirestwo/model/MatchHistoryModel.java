package com.aoeii.leaderboard.ageofempirestwo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class MatchHistoryModel {
    @JsonProperty("match_id")
    public String MATCH_ID;
    @JsonProperty("server")
    public String SERVER;

}
