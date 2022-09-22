package com.lastcivilization.statswriteservice.infrastructure.service.payment;

import com.lastcivilization.statswriteservice.domain.exception.ApplicationException;
import com.lastcivilization.statswriteservice.domain.exception.NotEnoughMoneyException;
import com.lastcivilization.statswriteservice.domain.port.PaymentService;
import feign.Feign;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class PaymentServiceAdapter implements PaymentService {

    private final PaymentClient paymentClient;

    @Override
    public void chargeAccount(String keycloakId, int cost) throws NotEnoughMoneyException {
        try{
            paymentClient.chargeAccount(keycloakId, cost);
        } catch (Exception exception){
            if(exception instanceof FeignException){
                if(( (FeignException) exception).status() == 400){
                    throw new NotEnoughMoneyException(cost);
                }
            }
            throw new ApplicationException(exception.getMessage());
        }
    }
}
