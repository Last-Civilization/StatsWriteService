package com.lastcivilization.statswriteservice.domain.dto;

public record StatsValueDto(
        Long id,
        int amount,
        TimeBonusDto timeBonus,
        String type,
        int total
) {

    //Move this to read service
    public StatsValueDto(Long id, int amount, TimeBonusDto timeBonus, String type){
        this(id, amount, timeBonus, type, amount + (amount * (timeBonus.amount() / 100)));
    }
}
