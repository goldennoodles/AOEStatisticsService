package com.aoeii.leaderboard.ageofempirestwo.repo;

import com.aoeii.leaderboard.ageofempirestwo.model.MatchHistoryModel;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AOEMatchRespository extends CrudRepository<MatchHistoryModel, Long> {

    List<MatchHistoryModel> findBySERVER(String match_id);
}
