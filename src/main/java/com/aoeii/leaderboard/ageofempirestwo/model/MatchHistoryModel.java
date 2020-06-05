package com.aoeii.leaderboard.ageofempirestwo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Getter
@Setter
@Component
@Table(name = "matchdata")
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class MatchHistoryModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;
    @NotNull
    @JsonProperty("match_id")
    private Long MATCH_ID;
    @JsonProperty("server")
    private String SERVER;
    @JsonProperty("match_uuid")
    private String MATCH_UUID;
    @JsonProperty("started")
    private String DATE_UNIX;
}
