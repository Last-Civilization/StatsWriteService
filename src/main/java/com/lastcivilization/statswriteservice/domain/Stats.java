package com.lastcivilization.statswriteservice.domain;

class Stats {

    private Long id;
    private Lvl lvl;
    private StatsValue damage;
    private StatsValue strength;
    private StatsValue dexterity;
    private StatsValue defense;
    private int health;

    Stats(Long id, Lvl lvl, StatsValue damage, StatsValue strength, StatsValue dexterity, StatsValue defense, int health) {
        this.id = id;
        this.lvl = lvl;
        this.damage = damage;
        this.strength = strength;
        this.dexterity = dexterity;
        this.defense = defense;
        this.health = health;
    }

    Long getId() {
        return id;
    }

    void setId(Long id) {
        this.id = id;
    }

    Lvl getLvl() {
        return lvl;
    }

    void setLvl(Lvl lvl) {
        this.lvl = lvl;
    }

    StatsValue getDamage() {
        return damage;
    }

    void setDamage(StatsValue damage) {
        this.damage = damage;
    }

    StatsValue getStrength() {
        return strength;
    }

    void setStrength(StatsValue strength) {
        this.strength = strength;
    }

    StatsValue getDexterity() {
        return dexterity;
    }

    void setDexterity(StatsValue dexterity) {
        this.dexterity = dexterity;
    }

    StatsValue getDefense() {
        return defense;
    }

    void setDefense(StatsValue defense) {
        this.defense = defense;
    }

    int getHealth() {
        return health;
    }

    void setHealth(int health) {
        this.health = health;
    }

    static final class Builder {

        private Long id;
        private Lvl lvl = Lvl.Builder.aLvl().build();
        private StatsValue damage = StatsValue.Builder.aStatsValue()
                .type(Type.DAMAGE)
                .build();
        private StatsValue strength = StatsValue.Builder.aStatsValue()
                .type(Type.STRENGTH)
                .build();
        private StatsValue dexterity = StatsValue.Builder.aStatsValue()
                .type(Type.DEXTERITY)
                .build();
        private StatsValue defense = StatsValue.Builder.aStatsValue()
                .type(Type.DEFENSE)
                .build();

        private int health = 100;

        private Builder() {
        }

        static Builder aStats() {
            return new Builder();
        }

        Builder id(Long id) {
            this.id = id;
            return this;
        }

        Builder lvl(Lvl lvl) {
            this.lvl = lvl;
            return this;
        }

        Builder damage(StatsValue damage) {
            this.damage = damage;
            return this;
        }

        Builder strength(StatsValue strength) {
            this.strength = strength;
            return this;
        }

        Builder dexterity(StatsValue dexterity) {
            this.dexterity = dexterity;
            return this;
        }

        Builder defense(StatsValue defense) {
            this.defense = defense;
            return this;
        }

        Builder health(int health){
            this.health = health;
            return this;
        }

        Stats build() {
            return new Stats(id, lvl, damage, strength, dexterity, defense, health);
        }
    }
}
