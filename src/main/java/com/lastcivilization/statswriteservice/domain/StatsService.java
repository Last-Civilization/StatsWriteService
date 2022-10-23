package com.lastcivilization.statswriteservice.domain;

import com.lastcivilization.statswriteservice.domain.view.StatsModel;
import com.lastcivilization.statswriteservice.domain.view.StatsValueModel;
import com.lastcivilization.statswriteservice.domain.port.dto.UserDto;
import com.lastcivilization.statswriteservice.domain.exception.StatsNotFoundException;
import com.lastcivilization.statswriteservice.domain.port.PaymentService;
import com.lastcivilization.statswriteservice.domain.port.StatsRepository;
import com.lastcivilization.statswriteservice.domain.port.UserService;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class StatsService {

    private final StatsRepository statsRepository;
    private final UserService userService;
    private final PaymentService paymentService;

    public StatsService(StatsRepository statsRepository, UserService userService, PaymentService paymentService) {
        this.statsRepository = statsRepository;
        this.userService = userService;
        this.paymentService = paymentService;
    }

    public StatsModel createStats() {
        Stats stats = buildStats();
        StatsModel statsModel = Mapper.toDto(stats);
        StatsModel savedStatsModel = statsRepository.save(statsModel);
        return savedStatsModel;
    }

    private Stats buildStats() {
        return Stats.Builder.aStats().build();
    }

    public StatsModel experienceUp(String keycloakId, int experience) {
        Stats stats = getStatsByKeycloakId(keycloakId);
        Lvl lvl = stats.getLvl();
        int currentLvl = lvl.getCurrent();
        int currentExperience = lvl.getExperience();
        currentExperience += experience;
        lvl.setExperience(currentExperience);
        if(experience > getExperienceToNextLvl(currentLvl)){
            lvl.setExperience(0);
            lvl.setCurrent(++currentLvl);
            int currentHealth = stats.getHealth();
            stats.setHealth(getHealthUpdate(currentHealth, currentLvl));
        }
        StatsModel savedStatsModel = statsRepository.save(Mapper.toDto(stats));
        return savedStatsModel;
    }

    private int getHealthUpdate(int currentHealth, int currentLvl) {
        return (currentHealth + (10 * currentLvl));
    }

    private StatsModel getStatById(long id) {
        return statsRepository.findById(id)
                .orElseThrow(() -> new StatsNotFoundException(id));
    }

    private int getExperienceToNextLvl(int lvl) {
        return ((lvl + 100) * (lvl / 2)) + 100;
    }

    public List<StatsValueModel> trainStrength(String keycloakId) {
        Stats stats = getStatsByKeycloakId(keycloakId);
        StatsValue strength = stats.getStrength();
        int currentStrength = strength.getAmount();
        int cost = getCostNextLvl(currentStrength);
        paymentService.chargeAccount(keycloakId, cost);
        strength.setAmount(++currentStrength);
        upDamageByNewStrength(currentStrength, stats);
        StatsModel savedStatsModel = statsRepository.save(Mapper.toDto(stats));
        return Arrays.asList(savedStatsModel.strength(), savedStatsModel.damage());
    }

    private int getCostNextLvl(int toTrain) {
        return (toTrain * 10) / 2;
    }

    private void upDamageByNewStrength(int currentStrength, Stats stats) {
        StatsValue damage = stats.getDamage();
        int currentDamage = damage.getAmount();
        damage.setAmount(currentDamage + getDamageUpdate(currentStrength));
    }

    private int getDamageUpdate(int currentStrength) {
        return ((currentStrength * 3) / 15) + 1;
    }

    public StatsValueModel trainDexterity(String keycloakId) {
        Stats stats = getStatsByKeycloakId(keycloakId);
        StatsValue dexterity = stats.getDexterity();
        int currentDexterity = dexterity.getAmount();
        int cost = getCostNextLvl(currentDexterity);
        paymentService.chargeAccount(keycloakId, cost);
        dexterity.setAmount(++currentDexterity);
        StatsModel savedStatsModel = statsRepository.save(Mapper.toDto(stats));
        return savedStatsModel.dexterity();
    }

    public StatsValueModel addTimeBonusToStrength(String keycloakId, int amount, int minutes) {
        Stats stats = getStatsByKeycloakId(keycloakId);
        StatsValue strength = stats.getStrength();
        TimeBonus timeBonus = buildTimeBonus(amount, minutes);
        strength.setTimeBonus(timeBonus);
        StatsModel savedStatsModel = statsRepository.save(Mapper.toDto(stats));
        return savedStatsModel.strength();
    }

    private TimeBonus buildTimeBonus(int amount, int minutes) {
        return TimeBonus.Builder.aTimeBonus()
                .amount(amount)
                .endDate(LocalDateTime.now().plusMinutes(minutes))
                .build();
    }

    public StatsValueModel addTimeBonusToDamage(String keycloakId, int amount, int minutes) {
        Stats stats = getStatsByKeycloakId(keycloakId);
        StatsValue damage = stats.getDamage();
        TimeBonus timeBonus = buildTimeBonus(amount, minutes);
        damage.setTimeBonus(timeBonus);
        StatsModel savedStatsModel = statsRepository.save(Mapper.toDto(stats));
        return savedStatsModel.damage();
    }

    public StatsValueModel addTimeBonusToDexterity(String keycloakId, int amount, int minutes) {
        Stats stats = getStatsByKeycloakId(keycloakId);
        StatsValue dexterity = stats.getDexterity();
        TimeBonus timeBonus = buildTimeBonus(amount, minutes);
        dexterity.setTimeBonus(timeBonus);
        StatsModel savedStatsModel = statsRepository.save(Mapper.toDto(stats));
        return savedStatsModel.dexterity();
    }

    private Stats getStatsByKeycloakId(String keycloakId) {
        UserDto userDto = userService.getUser(keycloakId);
        StatsModel statsModel = getStatById(userDto.stats());
        Stats stats = Mapper.toDomain(statsModel);
        return stats;
    }

    public StatsValueModel addTimeBonusToDefense(String keycloakId, int amount, int minutes) {
        Stats stats = getStatsByKeycloakId(keycloakId);
        StatsValue defense = stats.getDefense();
        TimeBonus timeBonus = buildTimeBonus(amount, minutes);
        defense.setTimeBonus(timeBonus);
        StatsModel savedStatsModel = statsRepository.save(Mapper.toDto(stats));
        return savedStatsModel.defense();
    }

    public void deleteById(long id) {
        statsRepository.deleteById(id);
    }
}
