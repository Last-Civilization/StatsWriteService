package com.lastcivilization.statswriteservice.domain;

import com.lastcivilization.statswriteservice.domain.dto.LvlDto;
import com.lastcivilization.statswriteservice.domain.dto.StatsDto;
import com.lastcivilization.statswriteservice.domain.dto.StatsValueDto;
import com.lastcivilization.statswriteservice.domain.dto.UserDto;
import com.lastcivilization.statswriteservice.domain.exception.NotEnoughMoneyException;
import com.lastcivilization.statswriteservice.domain.port.StatsRepository;
import com.lastcivilization.statswriteservice.domain.port.StatsService;
import com.lastcivilization.statswriteservice.domain.port.UserService;

public class StatsServiceImp implements StatsService {

    private final StatsRepository statsRepository;
    private final UserService userService;

    public StatsServiceImp(StatsRepository statsRepository, UserService userService) {
        this.statsRepository = statsRepository;
        this.userService = userService;
    }

    @Override
    public Long createStats() {
        Stats stats = buildStats();
        StatsDto statsDto = Mapper.toDto(stats);
        StatsDto savedStatsDto = statsRepository.save(statsDto);
        return savedStatsDto.id();
    }

    private Stats buildStats() {
        return Stats.Builder.aStats().build();
    }

    @Override
    public LvlDto experienceUp(String keycloakId, int experience) {
        UserDto userDto = userService.getUser(keycloakId);
        StatsDto statsDto = statsRepository.findById(userDto.stats());
        LvlDto lvlDto = statsDto.lvl();
        int lvl = lvlDto.current();
        int currentExperience = lvlDto.experience();
        currentExperience += experience;
        if(experience > getExperienceToNextLvl(lvl)){
            experience = 0;
            lvl++;
        }
        StatsDto savedStatsDto = statsRepository.save(statsDto);
        return savedStatsDto.lvl();
    }

    private int getExperienceToNextLvl(int lvl) {
        return ((lvl + 100) * (lvl / 2)) + 100;
    }

    @Override
    public StatsValueDto trainStrength(String keycloakId) {
        UserDto userDto = userService.getUser(keycloakId);
        StatsDto statsDto = statsRepository.findById(userDto.stats());
        StatsValueDto strength = statsDto.strength();
        int currentStrength = strength.amount();
        int cost = getCostNextLvl(currentStrength);
        userService.getMoneyFromUser(cost);
        currentStrength++;
        upDamageByNewStrength(currentStrength, statsDto);
        return null;
    }

    private int getCostNextLvl(int toTrain) {
        return (toTrain * 10) / 2;
    }

    private void upDamageByNewStrength(int currentStrength, StatsDto statsDto) {
        StatsValueDto damage = statsDto.damage();
        int currentDamage = damage.amount();
        currentDamage += getDamageUpdate(currentStrength);
    }

    private static int getDamageUpdate(int currentStrength) {
        return ((currentStrength * 3) / 15) + 1;
    }

    @Override
    public StatsValueDto trainDexterity(String keycloakId) {
        return null;
    }

    @Override
    public StatsValueDto giveTimeBonusToStrength(String keycloakId, int amount, int minutes) {
        return null;
    }

    @Override
    public StatsValueDto giveTimeBonusToDamage(String keycloakId, int amount, int minutes) {
        return null;
    }

    @Override
    public StatsValueDto giveTimeBonusToDexterity(String keycloakId, int amount, int minutes) {
        return null;
    }

    @Override
    public StatsValueDto giveTimeBonusToDefense(String keycloakId, int amount, int minutes) {
        return null;
    }
}
