package com.lastcivilization.statswriteservice.infrastructure.application.config;

import com.lastcivilization.statswriteservice.domain.StatsServiceImp;
import com.lastcivilization.statswriteservice.domain.dto.StatsDto;
import com.lastcivilization.statswriteservice.domain.dto.UserDto;
import com.lastcivilization.statswriteservice.domain.exception.NotEnoughMoneyException;
import com.lastcivilization.statswriteservice.domain.port.PaymentService;
import com.lastcivilization.statswriteservice.domain.port.StatsRepository;
import com.lastcivilization.statswriteservice.domain.port.StatsService;
import com.lastcivilization.statswriteservice.domain.port.UserService;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Optional;

@Configuration
@EntityScan("com.lastcivilization.statswriteservice.infrastructure.database")
@EnableJpaRepositories("com.lastcivilization.statswriteservice.infrastructure.database")
@ComponentScan("com.lastcivilization.statswriteservice.infrastructure")
class BeanConfiguration {

    @Bean
    StatsService statsService(StatsRepository statsRepository, UserService userService, PaymentService paymentService){
        return new StatsServiceImp(statsRepository, userService, paymentService);
    }

    @Bean
    StatsRepository statsRepository(){
        return new StatsRepository() {
            @Override
            public Optional<StatsDto> findById(Long id) {
                return Optional.empty();
            }

            @Override
            public StatsDto save(StatsDto statsDto) {
                return null;
            }
        };
    }

    @Bean
    UserService userService(){
        return new UserService() {
            @Override
            public UserDto getUser(String keycloakId) {
                return null;
            }
        };
    }

    @Bean
    PaymentService paymentService(){
        return new PaymentService() {
            @Override
            public void chargeAccount(String keycloakId, int cost) throws NotEnoughMoneyException {

            }
        };
    }
}