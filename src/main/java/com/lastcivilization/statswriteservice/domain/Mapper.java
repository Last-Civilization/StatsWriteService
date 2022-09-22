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
        Type type = statsValue.getType();
        return new StatsValueDto(
                statsValue.getId(),
                statsValue.getAmount(),
                toDto(statsValue.getTimeBonus()),
                type.toString()
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

    static Stats toDomain(StatsDto statsDto){
        return Stats.Builder.aStats()
                .id(statsDto.id())
                .lvl(toDomain(statsDto.lvl()))
                .damage(toDomain(statsDto.damage()))
                .strength(toDomain(statsDto.strength()))
                .dexterity(toDomain(statsDto.dexterity()))
                .defense(toDomain(statsDto.defense()))
                .build();
    }

    private static StatsValue toDomain(StatsValueDto statsValueDto) {
        return StatsValue.Builder.aStatsValue()
                .id(statsValueDto.id())
                .amount(statsValueDto.amount())
                .timeBonus(toDomain(statsValueDto.timeBonus()))
                .type(Type.valueOf(statsValueDto.type()))
                .build();
    }

    private static TimeBonus toDomain(TimeBonusDto timeBonus) {
        return TimeBonus.Builder.aTimeBonus()
                .id(timeBonus.id())
                .endDate(timeBonus.endDate())
                .amount(timeBonus.amount())
                .build();
    }

    private static Lvl toDomain(LvlDto lvlDto) {
        return Lvl.Builder.aLvl()
                .id(lvlDto.id())
                .current(lvlDto.current())
                .experience(lvlDto.experience())
                .build();
    }
}
