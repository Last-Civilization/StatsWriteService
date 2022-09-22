package com.lastcivilization.statswriteservice.infrastructure.application.rest;

import com.lastcivilization.statswriteservice.domain.dto.LvlDto;
import com.lastcivilization.statswriteservice.domain.dto.StatsDto;
import com.lastcivilization.statswriteservice.domain.dto.StatsValueDto;
import com.lastcivilization.statswriteservice.domain.port.StatsService;
import com.lastcivilization.statswriteservice.domain.port.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/stats")
class StatsController {

    private final StatsService statsService;

    @PostMapping
    StatsDto createStats(){
        return statsService.createStats();
    }

    @PatchMapping("/{keycloakId}/experiences/{amount}")
    LvlDto experienceUp(@PathVariable String keycloakId, @PathVariable int amount){
        return statsService.experienceUp(keycloakId, amount);
    }

    @PatchMapping("{keycloakId}/strengths")
    List<StatsValueDto> trainStrength(@PathVariable String keycloakId){
        return statsService.trainStrength(keycloakId);
    }

    @PatchMapping("{keycloakId}/dexterity")
    StatsValueDto trainDexterity(@PathVariable String keycloakId){
        return statsService.trainDexterity(keycloakId);
    }

    @PatchMapping("{keycloakId}/strengths/bonuses/{amount}/times/{time}")
    StatsValueDto giveTimeBonusToStrength(@PathVariable String keycloakId, @PathVariable int amount, @PathVariable int time){
        return statsService.giveTimeBonusToStrength(keycloakId, amount, time);
    }

    @PatchMapping("{keycloakId}/damages/bonuses/{amount}/times/{time}")
    StatsValueDto giveTimeBonusToDamage(@PathVariable String keycloakId, @PathVariable int amount, @PathVariable int time){
        return statsService.giveTimeBonusToDamage(keycloakId, amount, time);
    }

    @PatchMapping("{keycloakId}/dexterity/bonuses/{amount}/times/{time}")
    StatsValueDto giveTimeBonusToDexterity(@PathVariable String keycloakId, @PathVariable int amount, @PathVariable int time){
        return statsService.giveTimeBonusToDexterity(keycloakId, amount, time);
    }

    @PatchMapping("{keycloakId}/defences/bonuses/{amount}/times/{time}")
    StatsValueDto giveTimeBonusToDefence(@PathVariable String keycloakId, @PathVariable int amount, @PathVariable int time){
        return statsService.giveTimeBonusToDefense(keycloakId, amount, time);
    }
}
