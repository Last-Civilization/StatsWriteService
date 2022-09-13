package com.lastcivilization.statswriteservice.domain;

import com.lastcivilization.statswriteservice.domain.dto.LvlDto;
import com.lastcivilization.statswriteservice.domain.dto.StatsValueDto;
import com.lastcivilization.statswriteservice.domain.port.StatsRepository;
import com.lastcivilization.statswriteservice.domain.port.StatsService;
import com.lastcivilization.statswriteservice.domain.port.UserService;

public class StatsServiceImp implements StatsService {

    private final StatsRepository statsRepository;
    private final UserService userService;

    public StatsServiceImp(StatsRepository statsRepository, UserService userService) {
        this.statsRepository = statsRepository;
        this.userService = userService;
    }

    @Override
    public Long createStats() {
        return null;
    }

    @Override
    public LvlDto experienceUp(String keycloakId, int experience) {
        return null;
    }

    @Override
    public StatsValueDto trainStrength(String keycloakId) {
        return null;
    }

    @Override
    public StatsValueDto trainDexterity(String keycloakId) {
        return null;
    }

    @Override
    public StatsValueDto giveTimeBonusToStrength(String keycloakId, int amount, int minutes) {
        return null;
    }

    @Override
    public StatsValueDto giveTimeBonusToDamage(String keycloakId, int amount, int minutes) {
        return null;
    }

    @Override
    public StatsValueDto giveTimeBonusToDexterity(String keycloakId, int amount, int minutes) {
        return null;
    }

    @Override
    public StatsValueDto giveTimeBonusToDefense(String keycloakId, int amount, int minutes) {
        return null;
    }
}
