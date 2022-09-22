package com.lastcivilization.statswriteservice.infrastructure.service.payment;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("payment-write-service")
interface PaymentClient {

    @PatchMapping("/{keycloakId}/moneys/{amount}/charge")
    void chargeAccount(@PathVariable String keycloakId, @PathVariable int amount);
}
