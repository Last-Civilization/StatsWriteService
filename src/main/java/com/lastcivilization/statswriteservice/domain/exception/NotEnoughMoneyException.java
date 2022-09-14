package com.lastcivilization.statswriteservice.domain.exception;

public class NotEnoughMoneyException extends RuntimeException {

    public NotEnoughMoneyException(int neededMoney) {
        "U have not enough money, u need %d more!".formatted(neededMoney);
    }
}
