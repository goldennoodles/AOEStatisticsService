package com.aoeii.leaderboard.ageofempirestwo.repo;

import com.aoeii.leaderboard.ageofempirestwo.model.PlayerHistoryModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface AOERepository extends CrudRepository<PlayerHistoryModel, Long> {
    List<PlayerHistoryModel> findByNAME(String user);
}
