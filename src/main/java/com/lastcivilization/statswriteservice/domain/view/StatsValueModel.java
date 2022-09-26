package com.lastcivilization.statswriteservice.domain.view;

public record StatsValueModel(
        Long id,
        int amount,
        TimeBonusModel timeBonus,
        String type
) {
}
