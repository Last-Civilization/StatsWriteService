package com.lastcivilization.statswriteservice.domain;

class StatsValue {

    private Long id;
    private int amount;
    private TimeBonus timeBonus;
    private Type type;

    StatsValue(Long id, int amount, TimeBonus timeBonus, Type type) {
        this.id = id;
        this.amount = amount;
        this.timeBonus = timeBonus;
        this.type = type;
    }

    Long getId() {
        return id;
    }

    void setId(Long id) {
        this.id = id;
    }

    int getAmount() {
        return amount;
    }

    void setAmount(int amount) {
        this.amount = amount;
    }

    TimeBonus getTimeBonus() {
        return timeBonus;
    }

    void setTimeBonus(TimeBonus timeBonus) {
        this.timeBonus = timeBonus;
    }

    Type getType() {
        return type;
    }

    void setType(Type type) {
        this.type = type;
    }

    static final class Builder {

        private Long id;
        private int amount = 1;
        private TimeBonus timeBonus = TimeBonus.Builder.aTimeBonus().build();

        private Type type;

        private Builder() {
        }

        static Builder aStatsValue() {
            return new Builder();
        }

        Builder id(Long id) {
            this.id = id;
            return this;
        }

        Builder amount(int amount) {
            this.amount = amount;
            return this;
        }

        Builder timeBonus(TimeBonus timeBonus) {
            this.timeBonus = timeBonus;
            return this;
        }

        Builder type(Type type){
            this.type = type;
            return this;
        }

        StatsValue build() {
            return new StatsValue(id, amount, timeBonus, type);
        }
    }
}
