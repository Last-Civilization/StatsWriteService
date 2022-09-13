package com.lastcivilization.statswriteservice.domain.dto;

public class StatsValueDto {

    private Long id;
    private int amount;
    private TimeBonusDto timeBonus;

    public StatsValueDto(Long id, int amount, TimeBonusDto timeBonus) {
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

    public TimeBonusDto getTimeBonus() {
        return timeBonus;
    }

    public void setTimeBonus(TimeBonusDto timeBonus) {
        this.timeBonus = timeBonus;
    }

    public static final class Builder {

        private Long id;
        private int amount;
        private TimeBonusDto timeBonus;

        private Builder() {
        }

        public static Builder aStatsValueDto() {
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

        public Builder timeBonus(TimeBonusDto timeBonus) {
            this.timeBonus = timeBonus;
            return this;
        }

        public StatsValueDto build() {
            return new StatsValueDto(id, amount, timeBonus);
        }
    }
}
