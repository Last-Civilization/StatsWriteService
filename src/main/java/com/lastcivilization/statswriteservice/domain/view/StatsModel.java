package com.lastcivilization.statswriteservice.domain.view;

public record StatsModel(
        Long id,
        LvlModel lvl,
        StatsValueModel damage,
        StatsValueModel strength,
        StatsValueModel dexterity,
        StatsValueModel defense,
        int health
) {}
