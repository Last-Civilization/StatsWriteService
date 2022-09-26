package com.lastcivilization.statswriteservice.domain;

import com.lastcivilization.statswriteservice.domain.view.LvlModel;
import com.lastcivilization.statswriteservice.domain.view.StatsModel;
import com.lastcivilization.statswriteservice.domain.view.StatsValueModel;
import com.lastcivilization.statswriteservice.domain.view.TimeBonusModel;
import com.lastcivilization.statswriteservice.domain.port.dto.UserDto;
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
class StatsServiceTest {

    @Mock
    private StatsRepository statsRepository;

    @Mock
    private UserService userService;

    @Mock
    private PaymentService paymentService;

    @InjectMocks
    private StatsService underTest;

    @Test
    void shouldCreateStats() {
        //given
        doAnswer(invocationOnMock -> invocationOnMock.getArgument(0)).when(statsRepository).save(any(StatsModel.class));
        //when
        StatsModel statsModel = underTest.createStats();
        //then
        assertThat(statsModel.health()).isEqualTo(100);
        assertThat(statsModel.lvl().current()).isEqualTo(1);
        assertThat(statsModel.lvl().experience()).isEqualTo(0);
        assertThat(statsModel.damage().amount()).isEqualTo(1);
        assertThat(statsModel.damage().type()).isEqualTo("DAMAGE");
        assertThat(statsModel.damage().timeBonus().amount()).isEqualTo(0);
        assertThat(statsModel.damage().timeBonus().endDate().isBefore(LocalDateTime.now())).isTrue();
        assertThat(statsModel.strength().amount()).isEqualTo(1);
        assertThat(statsModel.strength().type()).isEqualTo("STRENGTH");
        assertThat(statsModel.strength().timeBonus().amount()).isEqualTo(0);
        assertThat(statsModel.strength().timeBonus().endDate().isBefore(LocalDateTime.now())).isTrue();
        assertThat(statsModel.dexterity().amount()).isEqualTo(1);
        assertThat(statsModel.dexterity().type()).isEqualTo("DEXTERITY");
        assertThat(statsModel.dexterity().timeBonus().amount()).isEqualTo(0);
        assertThat(statsModel.dexterity().timeBonus().endDate().isBefore(LocalDateTime.now())).isTrue();
        assertThat(statsModel.defense().amount()).isEqualTo(1);
        assertThat(statsModel.defense().type()).isEqualTo("DEFENSE");
        assertThat(statsModel.defense().timeBonus().amount()).isEqualTo(0);
        assertThat(statsModel.defense().timeBonus().endDate().isBefore(LocalDateTime.now())).isTrue();
    }

    @Test
    void shouldExperienceUp() {
        //given
        StatsModel testStatsModel = getTestStats();
        when(userService.getUser(anyString())).thenReturn(getTestUser());
        when(statsRepository.findById(anyLong())).thenReturn(Optional.of(testStatsModel));
        doAnswer(invocationOnMock -> invocationOnMock.getArgument(0)).when(statsRepository).save(any(StatsModel.class));
        //when
        StatsModel statsModel = underTest.experienceUp(anyString(), 100);
        //then
        LvlModel lvlModel = statsModel.lvl();
        assertThat(lvlModel.experience()).isEqualTo(100);
        assertThat(lvlModel.current()).isEqualTo(1);
        assertThat(statsModel.health()).isEqualTo(100);
    }

    @Test
    void shouldLvlUp() {
        //given
        StatsModel testStatsModel = getTestStats();
        when(userService.getUser(anyString())).thenReturn(getTestUser());
        when(statsRepository.findById(anyLong())).thenReturn(Optional.of(testStatsModel));
        doAnswer(invocationOnMock -> invocationOnMock.getArgument(0)).when(statsRepository).save(any(StatsModel.class));
        //when
        StatsModel statsModel = underTest.experienceUp(anyString(), 101);
        //then
        LvlModel lvlModel = statsModel.lvl();
        assertThat(lvlModel.experience()).isEqualTo(0);
        assertThat(lvlModel.current()).isEqualTo(2);
        assertThat(statsModel.health()).isEqualTo(120);
    }

    @NotNull
    private static UserDto getTestUser() {
        return new UserDto(0L, 0L);
    }

    private StatsModel getTestStats() {
        return new StatsModel(
                null,
                new LvlModel(
                        null,
                        1,
                        0
                ),
                new StatsValueModel(
                        null,
                        1,
                        new TimeBonusModel(
                                null,
                                LocalDateTime.now(),
                                0
                        ),
                        "DAMAGE"
                )
                ,
                new StatsValueModel(
                        null,
                        1,
                        new TimeBonusModel(
                                null,
                                LocalDateTime.now(),
                                0
                        ),
                        "STRENGTH"
                )
                ,
                new StatsValueModel(
                        null,
                        1,
                        new TimeBonusModel(
                                null,
                                LocalDateTime.now(),
                                0
                        ),
                        "DEXTERITY"
                )
                ,
                new StatsValueModel(
                        null,
                        1,
                        new TimeBonusModel(
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
        StatsModel statsModel = getTestStats();
        when(userService.getUser(anyString())).thenReturn(getTestUser());
        when(statsRepository.findById(anyLong())).thenReturn(Optional.of(statsModel));
        doNothing().when(paymentService).chargeAccount(anyString(), anyInt());
        doAnswer(invocationOnMock -> invocationOnMock.getArgument(0)).when(statsRepository).save(any(StatsModel.class));
        //when
        List<StatsValueModel> statsValueModel = underTest.trainStrength(anyString());
        //then
        assertThat(statsValueModel.get(0).amount()).isEqualTo(2);
        assertThat(statsValueModel.get(0).type()).isEqualTo("STRENGTH");
        assertThat(statsValueModel.get(1).amount()).isEqualTo(2);
        assertThat(statsValueModel.get(1).type()).isEqualTo("DAMAGE");
    }

    @Test
    void shouldTrainDexterity() {
        //given
        StatsModel statsModel = getTestStats();
        when(userService.getUser(anyString())).thenReturn(getTestUser());
        when(statsRepository.findById(anyLong())).thenReturn(Optional.of(statsModel));
        doNothing().when(paymentService).chargeAccount(anyString(), anyInt());
        doAnswer(invocationOnMock -> invocationOnMock.getArgument(0)).when(statsRepository).save(any(StatsModel.class));
        //when
        StatsValueModel statsValueModel = underTest.trainDexterity(anyString());
        //then
        assertThat(statsValueModel.amount()).isEqualTo(2);
        assertThat(statsValueModel.type()).isEqualTo("DEXTERITY");
    }

    @Test
    void shouldGiveTimeBonusToStrength() {
        //given
        StatsModel statsModel = getTestStats();
        when(userService.getUser(anyString())).thenReturn(getTestUser());
        when(statsRepository.findById(anyLong())).thenReturn(Optional.of(statsModel));
        doAnswer(invocationOnMock -> invocationOnMock.getArgument(0)).when(statsRepository).save(any(StatsModel.class));
        //when
        StatsValueModel statsValueModel = underTest.addTimeBonusToStrength(anyString(), 1, 1);
        //then
        TimeBonusModel timeBonusModel = statsValueModel.timeBonus();
        assertThat(statsValueModel.type()).isEqualTo("STRENGTH");
        assertThat(timeBonusModel.amount()).isEqualTo(1);
        assertThat(timeBonusModel.endDate().isAfter(LocalDateTime.now())).isTrue();
    }

    @Test
    void shouldGiveTimeBonusToDamage() {
        //given
        StatsModel statsModel = getTestStats();
        when(userService.getUser(anyString())).thenReturn(getTestUser());
        when(statsRepository.findById(anyLong())).thenReturn(Optional.of(statsModel));
        doAnswer(invocationOnMock -> invocationOnMock.getArgument(0)).when(statsRepository).save(any(StatsModel.class));
        //when
        StatsValueModel statsValueModel = underTest.addTimeBonusToDamage(anyString(), 1, 1);
        //then
        TimeBonusModel timeBonusModel = statsValueModel.timeBonus();
        assertThat(statsValueModel.type()).isEqualTo("DAMAGE");
        assertThat(timeBonusModel.amount()).isEqualTo(1);
        assertThat(timeBonusModel.endDate().isAfter(LocalDateTime.now())).isTrue();
    }

    @Test
    void shouldGiveTimeBonusToDexterity() {
        //given
        StatsModel statsModel = getTestStats();
        when(userService.getUser(anyString())).thenReturn(getTestUser());
        when(statsRepository.findById(anyLong())).thenReturn(Optional.of(statsModel));
        doAnswer(invocationOnMock -> invocationOnMock.getArgument(0)).when(statsRepository).save(any(StatsModel.class));
        //when
        StatsValueModel statsValueModel = underTest.addTimeBonusToDexterity(anyString(), 1, 1);
        //then
        TimeBonusModel timeBonusModel = statsValueModel.timeBonus();
        assertThat(statsValueModel.type()).isEqualTo("DEXTERITY");
        assertThat(timeBonusModel.amount()).isEqualTo(1);
        assertThat(timeBonusModel.endDate().isAfter(LocalDateTime.now())).isTrue();
    }

    @Test
    void shouldGiveTimeBonusToDefense() {
        //given
        StatsModel statsModel = getTestStats();
        when(userService.getUser(anyString())).thenReturn(getTestUser());
        when(statsRepository.findById(anyLong())).thenReturn(Optional.of(statsModel));
        doAnswer(invocationOnMock -> invocationOnMock.getArgument(0)).when(statsRepository).save(any(StatsModel.class));
        //when
        StatsValueModel statsValueModel = underTest.addTimeBonusToDefense(anyString(), 1, 1);
        //then
        TimeBonusModel timeBonusModel = statsValueModel.timeBonus();
        assertThat(statsValueModel.type()).isEqualTo("DEFENSE");
        assertThat(timeBonusModel.amount()).isEqualTo(1);
        assertThat(timeBonusModel.endDate().isAfter(LocalDateTime.now())).isTrue();
    }
}