package com.lastcivilization.statswriteservice.domain;

import java.time.LocalDateTime;

class TimeBonus {

    private Long id;
    private LocalDateTime endDate;
    private int amount;

    TimeBonus(Long id, LocalDateTime endDate, int amount) {
        this.id = id;
        this.endDate = endDate;
        this.amount = amount;
    }

    Long getId() {
        return id;
    }

    void setId(Long id) {
        this.id = id;
    }

    LocalDateTime getEndDate() {
        return endDate;
    }

    void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    int getAmount() {
        return amount;
    }

    void setAmount(int amount) {
        this.amount = amount;
    }

    static final class Builder {

        private Long id;
        private LocalDateTime endDate = LocalDateTime.now();
        private int amount;

        private Builder() {
        }

        static Builder aTimeBonus() {
            return new Builder();
        }

        Builder id(Long id) {
            this.id = id;
            return this;
        }

        Builder endDate(LocalDateTime endDate) {
            this.endDate = endDate;
            return this;
        }

        Builder amount(int amount) {
            this.amount = amount;
            return this;
        }

        TimeBonus build() {
            return new TimeBonus(id, endDate, amount);
        }
    }
}
