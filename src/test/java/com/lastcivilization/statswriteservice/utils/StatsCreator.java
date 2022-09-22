package com.lastcivilization.statswriteservice.utils;

import com.lastcivilization.statswriteservice.domain.dto.LvlDto;
import com.lastcivilization.statswriteservice.domain.dto.StatsDto;
import com.lastcivilization.statswriteservice.domain.dto.StatsValueDto;
import com.lastcivilization.statswriteservice.domain.dto.TimeBonusDto;
import com.lastcivilization.statswriteservice.domain.port.StatsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
class StatsCreator {

    private final StatsRepository statsRepository;

    public StatsDto getTestStatsAndResetDetails(){
        StatsDto statsDto = buildStats();
        return statsRepository.save(statsDto);
    }

    private StatsDto buildStats() {
        return new StatsDto(
                1L,
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
                )
        );
    }
}
