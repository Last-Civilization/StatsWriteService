package com.lastcivilization.statswriteservice.domain.port;

import com.lastcivilization.statswriteservice.domain.view.StatsModel;

import java.util.Optional;

public interface StatsRepository {

    Optional<StatsModel> findById(Long id);
    StatsModel save(StatsModel statsModel);
}
