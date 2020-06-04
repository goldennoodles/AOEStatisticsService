package com.aoeii.leaderboard.ageofempirestwo.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


@Getter
@Setter
@Component
@ConfigurationProperties
public class ApplicationProperties {

    @Value("${endpoints.aoe.user}")
    private String AOE_USER;

    @Value("${endpoints.aoe.match_count}")
    private String MATCH_COUNT;

    @Value("${endpoints.aoe.player_match_history}")
    private String PLAYER_MATCH_HISTORY;

    @Value("${endpoints.aoe.player_online_count}")
    private String PLAYER_ONLINE_GAME_COUNT;

    @Value("${endpoints.aoe.player_ranking_history}")
    private String PLAYER_RANKING_HISTORY;

    @Value("${endpoints.aoe.return_all_lobbies}")
    private String RETURN_ALL_LOBBIES;
    /*
        Jahan = 76561197989421548
        Hayden = 76561198080895557
        Ralph = 76561198236126814
        George = 76561198835871299
     */
}
