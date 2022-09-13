package com.lastcivilization.statswriteservice.domain;

import com.lastcivilization.statswriteservice.domain.dto.LvlDto;
import com.lastcivilization.statswriteservice.domain.dto.StatsDto;
import com.lastcivilization.statswriteservice.domain.dto.StatsValueDto;
import com.lastcivilization.statswriteservice.domain.dto.TimeBonusDto;

class Mapper {

    static StatsDto toDto(Stats stats){
        return new StatsDto(
                stats.getId(),
                toDto(stats.getLvl()),
                toDto(stats.getDamage()),
                toDto(stats.getStrength()),
                toDto(stats.getDexterity()),
                toDto(stats.getDefense())
        );
    }

    private static StatsValueDto toDto(StatsValue statsValue) {
        return new StatsValueDto(
                statsValue.getId(),
                statsValue.getAmount(),
                toDto(statsValue.getTimeBonus())
        );
    }

    private static TimeBonusDto toDto(TimeBonus timeBonus) {
        return new TimeBonusDto(
                timeBonus.getId(),
                timeBonus.getEndDate(),
                timeBonus.getAmount()
        );
    }

    private static LvlDto toDto(Lvl lvl) {
        return new LvlDto(
                lvl.getId(),
                lvl.getCurrent(),
                lvl.getExperience()
        );
    }
}
