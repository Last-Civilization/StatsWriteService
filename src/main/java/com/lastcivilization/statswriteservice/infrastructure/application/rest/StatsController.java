package com.lastcivilization.statswriteservice.infrastructure.application.rest;

import com.lastcivilization.statswriteservice.domain.StatsService;
import com.lastcivilization.statswriteservice.domain.view.StatsModel;
import com.lastcivilization.statswriteservice.domain.view.StatsValueModel;
import com.lastcivilization.statswriteservice.infrastructure.application.rest.dto.StatsDto;
import com.lastcivilization.statswriteservice.infrastructure.application.rest.dto.StatsValueDto;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Min;
import java.util.List;
import java.util.stream.Collectors;

import static com.lastcivilization.statswriteservice.infrastructure.application.rest.RestMapper.MAPPER;

@RestController
@RequiredArgsConstructor
@RequestMapping("/stats")
@Validated
class StatsController {

    private final StatsService statsService;

    @PostMapping
    StatsDto createStats(){
        StatsModel statsModel = statsService.createStats();
        return MAPPER.toDto(statsModel);
    }

    @PutMapping("/{keycloakId}/experiences/{amount}")
    StatsDto experienceUp(@PathVariable String keycloakId, @PathVariable @Min(1) int amount){
        StatsModel statsModel = statsService.experienceUp(keycloakId, amount);
        return MAPPER.toDto(statsModel);
    }

    @PutMapping("/{keycloakId}/strengths")
    List<StatsValueDto> trainStrength(@PathVariable String keycloakId){
        List<StatsValueModel> statsValueModels = statsService.trainStrength(keycloakId);
        return statsValueModels.stream()
                .map(MAPPER::toDto)
                .collect(Collectors.toList());
    }

    @PutMapping("/{keycloakId}/dexterity")
    StatsValueDto trainDexterity(@PathVariable String keycloakId){
        StatsValueModel statsValueModel = statsService.trainDexterity(keycloakId);
        return MAPPER.toDto(statsValueModel);
    }

    @PutMapping("/{keycloakId}/strengths/bonuses/{amount}/times/{time}")
    StatsValueDto giveTimeBonusToStrength(@PathVariable String keycloakId, @PathVariable @Min(1) int amount, @PathVariable @Min(1) int time){
        StatsValueModel statsValueModel = statsService.addTimeBonusToStrength(keycloakId, amount, time);
        return MAPPER.toDto(statsValueModel);
    }

    @PutMapping("/{keycloakId}/damages/bonuses/{amount}/times/{time}")
    StatsValueDto giveTimeBonusToDamage(@PathVariable String keycloakId, @PathVariable @Min(1) int amount, @PathVariable @Min(1) int time){
        StatsValueModel statsValueModel = statsService.addTimeBonusToDamage(keycloakId, amount, time);
        return MAPPER.toDto(statsValueModel);
    }

    @PutMapping("/{keycloakId}/dexterity/bonuses/{amount}/times/{time}")
    StatsValueDto giveTimeBonusToDexterity(@PathVariable String keycloakId, @PathVariable @Min(1) int amount, @PathVariable @Min(1) int time){
        StatsValueModel statsValueModel = statsService.addTimeBonusToDexterity(keycloakId, amount, time);
        return MAPPER.toDto(statsValueModel);
    }

    @PutMapping("/{keycloakId}/defences/bonuses/{amount}/times/{time}")
    StatsValueDto giveTimeBonusToDefence(@PathVariable String keycloakId, @PathVariable @Min(1) int amount, @PathVariable @Min(1) int time){
        StatsValueModel statsValueModel = statsService.addTimeBonusToDefense(keycloakId, amount, time);
        return MAPPER.toDto(statsValueModel);
    }
}
