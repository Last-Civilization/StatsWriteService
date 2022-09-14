package com.lastcivilization.statswriteservice.domain.dto;

public record StatsValueDto(
        Long id,
        int amount,
        TimeBonusDto timeBonus
) { }
