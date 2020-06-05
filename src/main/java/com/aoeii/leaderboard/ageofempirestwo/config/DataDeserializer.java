package com.aoeii.leaderboard.ageofempirestwo.config;

import com.aoeii.leaderboard.ageofempirestwo.model.MatchHistoryModel;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.DataFormatReaders;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;

public class DataDeserializer extends StdDeserializer<MatchHistoryModel> {

    public DataDeserializer(){
        this(null);
    }

    public DataDeserializer(Class<?> vc) {
        super(vc);
    }


    @Override
    public MatchHistoryModel deserialize(JsonParser jp, DeserializationContext ctxt)
            throws IOException, JsonProcessingException {
        JsonNode matchNode = jp.getCodec().readTree(jp);
        MatchHistoryModel model = new MatchHistoryModel();
        model.setDATE_UNIX(matchNode.get("started").textValue());
        model.setMATCH_ID(matchNode.get("match_id").longValue());
        model.setSERVER(matchNode.get("server").textValue());
        model.setSTEAM_ID(matchNode.get("steam_id").textValue());
        model.setMATCH_UUID(matchNode.get("match_uuid").textValue());
        model.setNAME(matchNode.get("players").get("name").textValue());
        model.setCOUNTRY(matchNode.get("players").get("country").textValue());
        model.setRATING(matchNode.get("players").get("rating").textValue());
        model.setWIN_STATEMENT(matchNode.get("players").get("won").textValue());
        return  model;



    }
}
