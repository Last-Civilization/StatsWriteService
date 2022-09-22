package com.lastcivilization.statswriteservice.domain;

class Stats {

    private Long id;
    private Lvl lvl;
    private StatsValue damage;
    private StatsValue strength;
    private StatsValue dexterity;
    private StatsValue defense;

    public Stats(Long id, Lvl lvl, StatsValue damage, StatsValue strength, StatsValue dexterity, StatsValue defense) {
        this.id = id;
        this.lvl = lvl;
        this.damage = damage;
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

    public Lvl getLvl() {
        return lvl;
    }

    public void setLvl(Lvl lvl) {
        this.lvl = lvl;
    }

    public StatsValue getDamage() {
        return damage;
    }

    public void setDamage(StatsValue damage) {
        this.damage = damage;
    }

    public StatsValue getStrength() {
        return strength;
    }

    public void setStrength(StatsValue strength) {
        this.strength = strength;
    }

    public StatsValue getDexterity() {
        return dexterity;
    }

    public void setDexterity(StatsValue dexterity) {
        this.dexterity = dexterity;
    }

    public StatsValue getDefense() {
        return defense;
    }

    public void setDefense(StatsValue defense) {
        this.defense = defense;
    }

    public static final class Builder {

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

        private Builder() {
        }

        public static Builder aStats() {
            return new Builder();
        }

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder lvl(Lvl lvl) {
            this.lvl = lvl;
            return this;
        }

        public Builder damage(StatsValue damage) {
            this.damage = damage;
            return this;
        }

        public Builder strength(StatsValue strength) {
            this.strength = strength;
            return this;
        }

        public Builder dexterity(StatsValue dexterity) {
            this.dexterity = dexterity;
            return this;
        }

        public Builder defense(StatsValue defense) {
            this.defense = defense;
            return this;
        }

        public Stats build() {
            return new Stats(id, lvl, damage, strength, dexterity, defense);
        }
    }
}
