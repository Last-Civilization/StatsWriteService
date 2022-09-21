package com.lastcivilization.statswriteservice.domain;

public class StatsValue {

    private Long id;
    private int amount;
    private TimeBonus timeBonus;

    public StatsValue(Long id, int amount, TimeBonus timeBonus) {
        this.id = id;
        this.amount = amount;
        this.timeBonus = timeBonus;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public TimeBonus getTimeBonus() {
        return timeBonus;
    }

    public void setTimeBonus(TimeBonus timeBonus) {
        this.timeBonus = timeBonus;
    }

    public int withBonus(){
        return amount + (amount * (timeBonus.getAmount() / 100));
    }

    public static final class Builder {

        private Long id;
        private int amount = 1;
        private TimeBonus timeBonus = TimeBonus.Builder.aTimeBonus().build();

        private Builder() {
        }

        public static Builder aStatsValue() {
            return new Builder();
        }

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder amount(int amount) {
            this.amount = amount;
            return this;
        }

        public Builder timeBonus(TimeBonus timeBonus) {
            this.timeBonus = timeBonus;
            return this;
        }

        public StatsValue build() {
            return new StatsValue(id, amount, timeBonus);
        }
    }
}
