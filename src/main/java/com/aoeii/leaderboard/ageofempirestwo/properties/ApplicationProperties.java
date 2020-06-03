package com.aoeii.leaderboard.ageofempirestwo.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;


@Getter
@Setter
@Component
public class ApplicationProperties {

    public final String AOE_API_LOCATION = "https://aoe2.net/api/strings?game=aoe2de&language=en";
    public final String AOE_LEADER_BOARD_LOCATION = "https://aoe2.net/api/leaderboard?game=aoe2de&leaderboard_id=3&start=1&count=1";
    public String AOE_USER = "76561198048932097"; // Make Dynamic (Current : Me)
    public final String PLAYER_MATCH_HISTORY = "https://aoe2.net/api/player/matches?game=aoe2de&steam_id=" + AOE_USER + "&count=5";

    /*
        Jahan = 76561197989421548
        Hayden = 76561198080895557
        Ralph = 76561198236126814
        George = 76561198835871299
     */
}
