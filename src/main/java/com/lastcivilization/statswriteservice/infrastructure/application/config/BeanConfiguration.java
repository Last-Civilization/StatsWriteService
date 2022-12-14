package com.lastcivilization.statswriteservice.infrastructure.application.config;

import com.lastcivilization.statswriteservice.domain.StatsService;
import com.lastcivilization.statswriteservice.domain.port.PaymentService;
import com.lastcivilization.statswriteservice.domain.port.StatsRepository;
import com.lastcivilization.statswriteservice.domain.port.UserService;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan("com.lastcivilization.statswriteservice.infrastructure.database")
@EnableJpaRepositories("com.lastcivilization.statswriteservice.infrastructure.database")
@ComponentScan("com.lastcivilization.statswriteservice")
class BeanConfiguration {

    @Bean
    StatsService statsService(StatsRepository statsRepository, UserService userService, PaymentService paymentService){
        return new StatsService(statsRepository, userService, paymentService);
    }
}
