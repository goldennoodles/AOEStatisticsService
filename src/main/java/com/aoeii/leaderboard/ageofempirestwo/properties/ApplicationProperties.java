package com.aoeii.leaderboard.ageofempirestwo.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;


@Getter
@Setter
@Component
public class ApplicationProperties {

    public String AOE_USER = ""; // Make Dynamic (Current : Me)
    public String MATCH_COUNT = "&count=5";
    public String PLAYER_MATCH_HISTORY = "https://aoe2.net/api/player/matches?game=aoe2de&steam_id=";
    public final String FULL_PLAYER_MATCH_HISTORY_URL = "https://aoe2.net/api/player/matches?game=aoe2de&steam_id=76561198048932097&count=5";
    public final String PLAYER_ONLINE_GAME_COUNT = "https://aoe2.net/api/stats/players?game=aoe2de";
    public final String PLAYER_RANKING_HISTORY = "https://aoe2.net/api/player/ratinghistory?game=aoe2de&leaderboard_id=4&steam_id=";
    public final String RETURN_ALL_LOBBIES = "https://aoe2.net/api/lobbies?game=aoe2de";
    /*
        Jahan = 76561197989421548
        Hayden = 76561198080895557
        Ralph = 76561198236126814
        George = 76561198835871299
     */
}
