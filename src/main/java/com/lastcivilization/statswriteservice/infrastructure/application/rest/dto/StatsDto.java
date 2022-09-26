package com.lastcivilization.statswriteservice.infrastructure.application.rest.dto;

public record StatsDto(
        Long id,
        LvlDto lvl,
        StatsValueDto damage,
        StatsValueDto strength,
        StatsValueDto dexterity,
        StatsValueDto defense,
        int health
) {}
