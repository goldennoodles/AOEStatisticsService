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
public class MatchHistoryModel {
    @JsonProperty("match_id")
    private Integer MATCH_ID;
    @JsonProperty("server")
    private String SERVER;
    @JsonProperty("match_uuid")
    private String MATCH_UUID;
    @JsonProperty("started")
    private String DATE_UNIX;
}
