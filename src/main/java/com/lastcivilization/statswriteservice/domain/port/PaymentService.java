package com.lastcivilization.statswriteservice.domain.port;

import com.lastcivilization.statswriteservice.domain.exception.NotEnoughMoneyException;

public interface PaymentService {

    void chargeAccount(String keycloakId, int cost) throws NotEnoughMoneyException;
}
