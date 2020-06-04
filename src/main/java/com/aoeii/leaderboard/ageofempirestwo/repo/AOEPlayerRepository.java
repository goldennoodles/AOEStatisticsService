package com.aoeii.leaderboard.ageofempirestwo.repo;

import com.aoeii.leaderboard.ageofempirestwo.model.PlayerHistoryModel;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface AOEPlayerRepository extends CrudRepository<PlayerHistoryModel, Long> {
    List<PlayerHistoryModel> findByNAME(String user);
}
