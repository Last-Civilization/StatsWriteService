package com.lastcivilization.statswriteservice.infrastructure.service.payment;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient(value = "payment-write-service", url = "${test.url:#{null}}")
interface PaymentClient {

    @PutMapping("/payments/{keycloakId}/moneys/{amount}/charge")
    void chargeAccount(@PathVariable String keycloakId, @PathVariable int amount);
}
