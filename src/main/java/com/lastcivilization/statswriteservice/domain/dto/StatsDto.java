package com.lastcivilization.statswriteservice.domain.dto;

public class StatsDto {

    private Long id;
    private LvlDto lvl;
    private StatsValueDto restistance;
    private StatsValueDto damge;
    private StatsValueDto strength;
    private StatsValueDto dexterity;
    private StatsValueDto defense;

    public StatsDto(Long id, LvlDto lvl, StatsValueDto restistance, StatsValueDto damge, StatsValueDto strength, StatsValueDto dexterity,
                    StatsValueDto defense) {
        this.id = id;
        this.lvl = lvl;
        this.restistance = restistance;
        this.damge = damge;
        this.strength = strength;
        this.dexterity = dexterity;
        this.defense = defense;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LvlDto getLvl() {
        return lvl;
    }

    public void setLvl(LvlDto lvl) {
        this.lvl = lvl;
    }

    public StatsValueDto getRestistance() {
        return restistance;
    }

    public void setRestistance(StatsValueDto restistance) {
        this.restistance = restistance;
    }

    public StatsValueDto getDamge() {
        return damge;
    }

    public void setDamge(StatsValueDto damge) {
        this.damge = damge;
    }

    public StatsValueDto getStrength() {
        return strength;
    }

    public void setStrength(StatsValueDto strength) {
        this.strength = strength;
    }

    public StatsValueDto getDexterity() {
        return dexterity;
    }

    public void setDexterity(StatsValueDto dexterity) {
        this.dexterity = dexterity;
    }

    public StatsValueDto getDefense() {
        return defense;
    }

    public void setDefense(StatsValueDto defense) {
        this.defense = defense;
    }

    public static final class Builder {

        private Long id;
        private LvlDto lvl;
        private StatsValueDto restistance;
        private StatsValueDto damge;
        private StatsValueDto strength;
        private StatsValueDto dexterity;
        private StatsValueDto defense;

        private Builder() {
        }

        public static Builder aStatsDto() {
            return new Builder();
        }

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder lvl(LvlDto lvl) {
            this.lvl = lvl;
            return this;
        }

        public Builder restistance(StatsValueDto restistance) {
            this.restistance = restistance;
            return this;
        }

        public Builder damge(StatsValueDto damge) {
            this.damge = damge;
            return this;
        }

        public Builder strength(StatsValueDto strength) {
            this.strength = strength;
            return this;
        }

        public Builder dexterity(StatsValueDto dexterity) {
            this.dexterity = dexterity;
            return this;
        }

        public Builder defense(StatsValueDto defense) {
            this.defense = defense;
            return this;
        }

        public StatsDto build() {
            return new StatsDto(id, lvl, restistance, damge, strength, dexterity, defense);
        }
    }
}
