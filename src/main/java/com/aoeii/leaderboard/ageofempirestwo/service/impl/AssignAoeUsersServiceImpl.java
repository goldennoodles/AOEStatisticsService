package com.aoeii.leaderboard.ageofempirestwo.service.impl;

import com.aoeii.leaderboard.ageofempirestwo.properties.ApplicationProperties;
import com.aoeii.leaderboard.ageofempirestwo.service.AssignAoeUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AssignAoeUsersServiceImpl implements AssignAoeUsersService {

    @Autowired
    private ApplicationProperties applicationProperties;

    @Override
    public void AddUserToDataList(String user) {

    }
}
