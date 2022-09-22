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
    StatsValueDto addTimeBonusToStrength(String keycloakId, int amount, int minutes);
    StatsValueDto addTimeBonusToDamage(String keycloakId, int amount, int minutes);
    StatsValueDto addTimeBonusToDexterity(String keycloakId, int amount, int minutes);
    StatsValueDto addTimeBonusToDefense(String keycloakId, int amount, int minutes);
}
