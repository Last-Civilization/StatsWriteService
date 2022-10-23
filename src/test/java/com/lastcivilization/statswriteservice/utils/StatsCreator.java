package com.lastcivilization.statswriteservice.utils;

import com.lastcivilization.statswriteservice.domain.view.LvlModel;
import com.lastcivilization.statswriteservice.domain.view.StatsModel;
import com.lastcivilization.statswriteservice.domain.view.StatsValueModel;
import com.lastcivilization.statswriteservice.domain.view.TimeBonusModel;
import com.lastcivilization.statswriteservice.domain.port.StatsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class StatsCreator {

    private final StatsRepository statsRepository;

    StatsModel resetTestStatsDetails(){
        StatsModel statsModel = buildStats(1L);
        return statsRepository.save(statsModel);
    }

    private StatsModel buildStats(long id) {
        return new StatsModel(
                id,
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

    public void createTestStatsToDelete(){
        StatsModel statsModel = buildStats(2L);
        statsRepository.save(statsModel);
    }
}
