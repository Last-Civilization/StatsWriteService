package com.lastcivilization.statswriteservice.domain;

import com.lastcivilization.statswriteservice.domain.view.LvlModel;
import com.lastcivilization.statswriteservice.domain.view.StatsModel;
import com.lastcivilization.statswriteservice.domain.view.StatsValueModel;
import com.lastcivilization.statswriteservice.domain.view.TimeBonusModel;

final class Mapper {

    private Mapper(){
        throw new RuntimeException("You can not create instance of this class!");
    }

    static StatsModel toDto(Stats stats){
        return new StatsModel(
                stats.getId(),
                toDto(stats.getLvl()),
                toDto(stats.getDamage()),
                toDto(stats.getStrength()),
                toDto(stats.getDexterity()),
                toDto(stats.getDefense()),
                stats.getHealth()
        );
    }

    private static StatsValueModel toDto(StatsValue statsValue) {
        Type type = statsValue.getType();
        return new StatsValueModel(
                statsValue.getId(),
                statsValue.getAmount(),
                toDto(statsValue.getTimeBonus()),
                type.toString()
        );
    }

    private static TimeBonusModel toDto(TimeBonus timeBonus) {
        return new TimeBonusModel(
                timeBonus.getId(),
                timeBonus.getEndDate(),
                timeBonus.getAmount()
        );
    }

    private static LvlModel toDto(Lvl lvl) {
        return new LvlModel(
                lvl.getId(),
                lvl.getCurrent(),
                lvl.getExperience()
        );
    }

    static Stats toDomain(StatsModel statsModel){
        return Stats.Builder.aStats()
                .id(statsModel.id())
                .lvl(toDomain(statsModel.lvl()))
                .damage(toDomain(statsModel.damage()))
                .strength(toDomain(statsModel.strength()))
                .dexterity(toDomain(statsModel.dexterity()))
                .defense(toDomain(statsModel.defense()))
                .health(statsModel.health())
                .build();
    }

    private static StatsValue toDomain(StatsValueModel statsValueModel) {
        return StatsValue.Builder.aStatsValue()
                .id(statsValueModel.id())
                .amount(statsValueModel.amount())
                .timeBonus(toDomain(statsValueModel.timeBonus()))
                .type(Type.valueOf(statsValueModel.type()))
                .build();
    }

    private static TimeBonus toDomain(TimeBonusModel timeBonus) {
        return TimeBonus.Builder.aTimeBonus()
                .id(timeBonus.id())
                .endDate(timeBonus.endDate())
                .amount(timeBonus.amount())
                .build();
    }

    private static Lvl toDomain(LvlModel lvlModel) {
        return Lvl.Builder.aLvl()
                .id(lvlModel.id())
                .current(lvlModel.current())
                .experience(lvlModel.experience())
                .build();
    }
}
