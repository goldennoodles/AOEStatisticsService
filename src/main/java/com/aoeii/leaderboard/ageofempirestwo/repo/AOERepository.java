package com.aoeii.leaderboard.ageofempirestwo.repo;

import com.aoeii.leaderboard.ageofempirestwo.model.PlayerHistoryModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AOERepository extends JpaRepository<PlayerHistoryModel, String> {
    @Query("SELECT * FROM aoedata WHERE u.user like %?1")
    List<PlayerHistoryModel> findUserData(String user);
}
