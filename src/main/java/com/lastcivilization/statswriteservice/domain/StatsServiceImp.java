package com.lastcivilization.statswriteservice.domain;

import com.lastcivilization.statswriteservice.domain.dto.LvlDto;
import com.lastcivilization.statswriteservice.domain.dto.StatsDto;
import com.lastcivilization.statswriteservice.domain.dto.StatsValueDto;
import com.lastcivilization.statswriteservice.domain.dto.UserDto;
import com.lastcivilization.statswriteservice.domain.exception.StatsNotFoundException;
import com.lastcivilization.statswriteservice.domain.port.PaymentService;
import com.lastcivilization.statswriteservice.domain.port.StatsRepository;
import com.lastcivilization.statswriteservice.domain.port.StatsService;
import com.lastcivilization.statswriteservice.domain.port.UserService;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static com.lastcivilization.statswriteservice.domain.Mapper.toDomain;
import static com.lastcivilization.statswriteservice.domain.Mapper.toDto;

public class StatsServiceImp implements StatsService {

    private final StatsRepository statsRepository;
    private final UserService userService;
    private final PaymentService paymentService;

    public StatsServiceImp(StatsRepository statsRepository, UserService userService, PaymentService paymentService) {
        this.statsRepository = statsRepository;
        this.userService = userService;
        this.paymentService = paymentService;
    }

    @Override
    public StatsDto createStats() {
        Stats stats = buildStats();
        StatsDto statsDto = toDto(stats);
        StatsDto savedStatsDto = statsRepository.save(statsDto);
        return savedStatsDto;
    }

    private Stats buildStats() {
        return Stats.Builder.aStats().build();
    }

    @Override
    public LvlDto experienceUp(String keycloakId, int experience) {
        Stats stats = getStatsByKeycloakId(keycloakId);
        Lvl lvl = stats.getLvl();
        int currentLvl = lvl.getCurrent();
        int currentExperience = lvl.getExperience();
        currentExperience += experience;
        lvl.setExperience(currentExperience);
        if(experience > getExperienceToNextLvl(currentLvl)){
            lvl.setExperience(0);
            lvl.setCurrent(++currentLvl);
        }
        StatsDto savedStatsDto = statsRepository.save(toDto(stats));
        return savedStatsDto.lvl();
    }

    private StatsDto getStatById(long id) {
        return statsRepository.findById(id)
                .orElseThrow(() -> new StatsNotFoundException(id));
    }

    private int getExperienceToNextLvl(int lvl) {
        return ((lvl + 100) * (lvl / 2)) + 100;
    }

    @Override
    public List<StatsValueDto> trainStrength(String keycloakId) {
        Stats stats = getStatsByKeycloakId(keycloakId);
        StatsValue strength = stats.getStrength();
        int currentStrength = strength.getAmount();
        int cost = getCostNextLvl(currentStrength);
        paymentService.chargeAccount(keycloakId, cost);
        strength.setAmount(++currentStrength);
        upDamageByNewStrength(currentStrength, stats);
        StatsDto savedStatsDto = statsRepository.save(toDto(stats));
        return Arrays.asList(savedStatsDto.strength(), savedStatsDto.damage());
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

    @Override
    public StatsValueDto trainDexterity(String keycloakId) {
        Stats stats = getStatsByKeycloakId(keycloakId);
        StatsValue dexterity = stats.getDexterity();
        int currentDexterity = dexterity.getAmount();
        int cost = getCostNextLvl(currentDexterity);
        paymentService.chargeAccount(keycloakId, cost);
        dexterity.setAmount(++currentDexterity);
        StatsDto savedStatsDto = statsRepository.save(toDto(stats));
        return savedStatsDto.dexterity();
    }

    @Override
    public StatsValueDto giveTimeBonusToStrength(String keycloakId, int amount, int minutes) {
        Stats stats = getStatsByKeycloakId(keycloakId);
        StatsValue strength = stats.getStrength();
        TimeBonus timeBonus = buildTimeBonus(amount, minutes);
        strength.setTimeBonus(timeBonus);
        StatsDto savedStatsDto = statsRepository.save(toDto(stats));
        return savedStatsDto.strength();
    }

    private TimeBonus buildTimeBonus(int amount, int minutes) {
        return TimeBonus.Builder.aTimeBonus()
                .amount(amount)
                .endDate(LocalDateTime.now().plusMinutes(minutes))
                .build();
    }

    @Override
    public StatsValueDto giveTimeBonusToDamage(String keycloakId, int amount, int minutes) {
        Stats stats = getStatsByKeycloakId(keycloakId);
        StatsValue damage = stats.getDamage();
        TimeBonus timeBonus = buildTimeBonus(amount, minutes);
        damage.setTimeBonus(timeBonus);
        StatsDto savedStatsDto = statsRepository.save(toDto(stats));
        return savedStatsDto.damage();
    }

    @Override
    public StatsValueDto giveTimeBonusToDexterity(String keycloakId, int amount, int minutes) {
        Stats stats = getStatsByKeycloakId(keycloakId);
        StatsValue dexterity = stats.getDexterity();
        TimeBonus timeBonus = buildTimeBonus(amount, minutes);
        dexterity.setTimeBonus(timeBonus);
        StatsDto savedStatsDto = statsRepository.save(toDto(stats));
        return savedStatsDto.dexterity();
    }

    private Stats getStatsByKeycloakId(String keycloakId) {
        UserDto userDto = userService.getUser(keycloakId);
        StatsDto statsDto = getStatById(userDto.stats());
        Stats stats = toDomain(statsDto);
        return stats;
    }

    @Override
    public StatsValueDto giveTimeBonusToDefense(String keycloakId, int amount, int minutes) {
        Stats stats = getStatsByKeycloakId(keycloakId);
        StatsValue defense = stats.getDefense();
        TimeBonus timeBonus = buildTimeBonus(amount, minutes);
        defense.setTimeBonus(timeBonus);
        StatsDto savedStatsDto = statsRepository.save(toDto(stats));
        return savedStatsDto.defense();
    }
}
