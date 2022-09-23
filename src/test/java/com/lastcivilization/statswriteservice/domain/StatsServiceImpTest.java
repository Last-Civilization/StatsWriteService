package com.lastcivilization.statswriteservice.domain;

import com.lastcivilization.statswriteservice.domain.dto.LvlDto;
import com.lastcivilization.statswriteservice.domain.dto.StatsDto;
import com.lastcivilization.statswriteservice.domain.dto.StatsValueDto;
import com.lastcivilization.statswriteservice.domain.dto.TimeBonusDto;
import com.lastcivilization.statswriteservice.domain.dto.UserDto;
import com.lastcivilization.statswriteservice.domain.port.PaymentService;
import com.lastcivilization.statswriteservice.domain.port.StatsRepository;
import com.lastcivilization.statswriteservice.domain.port.UserService;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StatsServiceImpTest {

    @Mock
    private StatsRepository statsRepository;

    @Mock
    private UserService userService;

    @Mock
    private PaymentService paymentService;

    @InjectMocks
    private StatsServiceImp underTest;

    @Test
    void shouldCreateStats() {
        //given
        doAnswer(invocationOnMock -> invocationOnMock.getArgument(0)).when(statsRepository).save(any(StatsDto.class));
        //when
        StatsDto statsDto = underTest.createStats();
        //then
        assertThat(statsDto.health()).isEqualTo(100);
        assertThat(statsDto.lvl().current()).isEqualTo(1);
        assertThat(statsDto.lvl().experience()).isEqualTo(0);
        assertThat(statsDto.damage().amount()).isEqualTo(1);
        assertThat(statsDto.damage().type()).isEqualTo("DAMAGE");
        assertThat(statsDto.damage().timeBonus().amount()).isEqualTo(0);
        assertThat(statsDto.damage().timeBonus().endDate().isBefore(LocalDateTime.now())).isTrue();
        assertThat(statsDto.strength().amount()).isEqualTo(1);
        assertThat(statsDto.strength().type()).isEqualTo("STRENGTH");
        assertThat(statsDto.strength().timeBonus().amount()).isEqualTo(0);
        assertThat(statsDto.strength().timeBonus().endDate().isBefore(LocalDateTime.now())).isTrue();
        assertThat(statsDto.dexterity().amount()).isEqualTo(1);
        assertThat(statsDto.dexterity().type()).isEqualTo("DEXTERITY");
        assertThat(statsDto.dexterity().timeBonus().amount()).isEqualTo(0);
        assertThat(statsDto.dexterity().timeBonus().endDate().isBefore(LocalDateTime.now())).isTrue();
        assertThat(statsDto.defense().amount()).isEqualTo(1);
        assertThat(statsDto.defense().type()).isEqualTo("DEFENSE");
        assertThat(statsDto.defense().timeBonus().amount()).isEqualTo(0);
        assertThat(statsDto.defense().timeBonus().endDate().isBefore(LocalDateTime.now())).isTrue();
    }

    @Test
    void shouldExperienceUp() {
        //given
        StatsDto testStatsDto = getTestStats();
        when(userService.getUser(anyString())).thenReturn(getTestUser());
        when(statsRepository.findById(anyLong())).thenReturn(Optional.of(testStatsDto));
        doAnswer(invocationOnMock -> invocationOnMock.getArgument(0)).when(statsRepository).save(any(StatsDto.class));
        //when
        StatsDto statsDto = underTest.experienceUp(anyString(), 100);
        //then
        LvlDto lvlDto = statsDto.lvl();
        assertThat(lvlDto.experience()).isEqualTo(100);
        assertThat(lvlDto.current()).isEqualTo(1);
        assertThat(statsDto.health()).isEqualTo(100);
    }

    @Test
    void shouldLvlUp() {
        //given
        StatsDto testStatsDto = getTestStats();
        when(userService.getUser(anyString())).thenReturn(getTestUser());
        when(statsRepository.findById(anyLong())).thenReturn(Optional.of(testStatsDto));
        doAnswer(invocationOnMock -> invocationOnMock.getArgument(0)).when(statsRepository).save(any(StatsDto.class));
        //when
        StatsDto statsDto = underTest.experienceUp(anyString(), 101);
        //then
        LvlDto lvlDto = statsDto.lvl();
        assertThat(lvlDto.experience()).isEqualTo(0);
        assertThat(lvlDto.current()).isEqualTo(2);
        assertThat(statsDto.health()).isEqualTo(120);
    }

    @NotNull
    private static UserDto getTestUser() {
        return new UserDto(0L, 0L);
    }

    private StatsDto getTestStats() {
        return new StatsDto(
                null,
                new LvlDto(
                        null,
                        1,
                        0
                ),
                new StatsValueDto(
                        null,
                        1,
                        new TimeBonusDto(
                                null,
                                LocalDateTime.now(),
                                0
                        ),
                        "DAMAGE"
                )
                ,
                new StatsValueDto(
                        null,
                        1,
                        new TimeBonusDto(
                                null,
                                LocalDateTime.now(),
                                0
                        ),
                        "STRENGTH"
                )
                ,
                new StatsValueDto(
                        null,
                        1,
                        new TimeBonusDto(
                                null,
                                LocalDateTime.now(),
                                0
                        ),
                        "DEXTERITY"
                )
                ,
                new StatsValueDto(
                        null,
                        1,
                        new TimeBonusDto(
                                null,
                                LocalDateTime.now(),
                                0
                        ),
                        "DEFENSE"
                ),
                100
        );
    }

    @Test
    void shouldTrainStrength() {
        //given
        StatsDto statsDto = getTestStats();
        when(userService.getUser(anyString())).thenReturn(getTestUser());
        when(statsRepository.findById(anyLong())).thenReturn(Optional.of(statsDto));
        doNothing().when(paymentService).chargeAccount(anyString(), anyInt());
        doAnswer(invocationOnMock -> invocationOnMock.getArgument(0)).when(statsRepository).save(any(StatsDto.class));
        //when
        List<StatsValueDto> statsValueDto = underTest.trainStrength(anyString());
        //then
        assertThat(statsValueDto.get(0).amount()).isEqualTo(2);
        assertThat(statsValueDto.get(0).type()).isEqualTo("STRENGTH");
        assertThat(statsValueDto.get(1).amount()).isEqualTo(2);
        assertThat(statsValueDto.get(1).type()).isEqualTo("DAMAGE");
    }

    @Test
    void shouldTrainDexterity() {
        //given
        StatsDto statsDto = getTestStats();
        when(userService.getUser(anyString())).thenReturn(getTestUser());
        when(statsRepository.findById(anyLong())).thenReturn(Optional.of(statsDto));
        doNothing().when(paymentService).chargeAccount(anyString(), anyInt());
        doAnswer(invocationOnMock -> invocationOnMock.getArgument(0)).when(statsRepository).save(any(StatsDto.class));
        //when
        StatsValueDto statsValueDto = underTest.trainDexterity(anyString());
        //then
        assertThat(statsValueDto.amount()).isEqualTo(2);
        assertThat(statsValueDto.type()).isEqualTo("DEXTERITY");
    }

    @Test
    void shouldGiveTimeBonusToStrength() {
        //given
        StatsDto statsDto = getTestStats();
        when(userService.getUser(anyString())).thenReturn(getTestUser());
        when(statsRepository.findById(anyLong())).thenReturn(Optional.of(statsDto));
        doAnswer(invocationOnMock -> invocationOnMock.getArgument(0)).when(statsRepository).save(any(StatsDto.class));
        //when
        StatsValueDto statsValueDto = underTest.addTimeBonusToStrength(anyString(), 1, 1);
        //then
        TimeBonusDto timeBonusDto = statsValueDto.timeBonus();
        assertThat(statsValueDto.type()).isEqualTo("STRENGTH");
        assertThat(timeBonusDto.amount()).isEqualTo(1);
        assertThat(timeBonusDto.endDate().isAfter(LocalDateTime.now())).isTrue();
    }

    @Test
    void shouldGiveTimeBonusToDamage() {
        //given
        StatsDto statsDto = getTestStats();
        when(userService.getUser(anyString())).thenReturn(getTestUser());
        when(statsRepository.findById(anyLong())).thenReturn(Optional.of(statsDto));
        doAnswer(invocationOnMock -> invocationOnMock.getArgument(0)).when(statsRepository).save(any(StatsDto.class));
        //when
        StatsValueDto statsValueDto = underTest.addTimeBonusToDamage(anyString(), 1, 1);
        //then
        TimeBonusDto timeBonusDto = statsValueDto.timeBonus();
        assertThat(statsValueDto.type()).isEqualTo("DAMAGE");
        assertThat(timeBonusDto.amount()).isEqualTo(1);
        assertThat(timeBonusDto.endDate().isAfter(LocalDateTime.now())).isTrue();
    }

    @Test
    void shouldGiveTimeBonusToDexterity() {
        //given
        StatsDto statsDto = getTestStats();
        when(userService.getUser(anyString())).thenReturn(getTestUser());
        when(statsRepository.findById(anyLong())).thenReturn(Optional.of(statsDto));
        doAnswer(invocationOnMock -> invocationOnMock.getArgument(0)).when(statsRepository).save(any(StatsDto.class));
        //when
        StatsValueDto statsValueDto = underTest.addTimeBonusToDexterity(anyString(), 1, 1);
        //then
        TimeBonusDto timeBonusDto = statsValueDto.timeBonus();
        assertThat(statsValueDto.type()).isEqualTo("DEXTERITY");
        assertThat(timeBonusDto.amount()).isEqualTo(1);
        assertThat(timeBonusDto.endDate().isAfter(LocalDateTime.now())).isTrue();
    }

    @Test
    void shouldGiveTimeBonusToDefense() {
        //given
        StatsDto statsDto = getTestStats();
        when(userService.getUser(anyString())).thenReturn(getTestUser());
        when(statsRepository.findById(anyLong())).thenReturn(Optional.of(statsDto));
        doAnswer(invocationOnMock -> invocationOnMock.getArgument(0)).when(statsRepository).save(any(StatsDto.class));
        //when
        StatsValueDto statsValueDto = underTest.addTimeBonusToDefense(anyString(), 1, 1);
        //then
        TimeBonusDto timeBonusDto = statsValueDto.timeBonus();
        assertThat(statsValueDto.type()).isEqualTo("DEFENSE");
        assertThat(timeBonusDto.amount()).isEqualTo(1);
        assertThat(timeBonusDto.endDate().isAfter(LocalDateTime.now())).isTrue();
    }
}