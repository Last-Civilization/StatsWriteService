package com.lastcivilization.statswriteservice.domain.port;

import com.lastcivilization.statswriteservice.domain.dto.LvlDto;
import com.lastcivilization.statswriteservice.domain.dto.StatsDto;
import com.lastcivilization.statswriteservice.domain.dto.StatsValueDto;

import java.util.List;

public interface StatsService {

    StatsDto createStats();
    LvlDto experienceUp(String keycloakId, int experience);
    List<StatsValueDto> trainStrength(String keycloakId);
    StatsValueDto trainDexterity(String keycloakId);
    StatsValueDto giveTimeBonusToStrength(String keycloakId, int amount, int minutes);
    StatsValueDto giveTimeBonusToDamage(String keycloakId, int amount, int minutes);
    StatsValueDto giveTimeBonusToDexterity(String keycloakId, int amount, int minutes);
    StatsValueDto giveTimeBonusToDefense(String keycloakId, int amount, int minutes);
}
