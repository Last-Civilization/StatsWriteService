package com.lastcivilization.statswriteservice.domain;

import java.time.LocalDateTime;

class TimeBonus {

    private Long id;
    private LocalDateTime endDate;
    private int amount = 0;

    public TimeBonus(Long id, LocalDateTime endDate, int amount) {
        this.id = id;
        this.endDate = endDate;
        this.amount = amount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public static final class Builder {

        private Long id;
        private LocalDateTime endDate;
        private int amount;

        private Builder() {
        }

        public static Builder aTimeBonus() {
            return new Builder();
        }

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder endDate(LocalDateTime endDate) {
            this.endDate = endDate;
            return this;
        }

        public Builder amount(int amount) {
            this.amount = amount;
            return this;
        }

        public TimeBonus build() {
            return new TimeBonus(id, endDate, amount);
        }
    }
}
