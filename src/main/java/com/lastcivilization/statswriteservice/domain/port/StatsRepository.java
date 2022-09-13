package com.lastcivilization.statswriteservice.domain.port;

import com.lastcivilization.statswriteservice.domain.dto.StatsDto;

public interface StatsRepository {

    StatsDto findById(Long id);
    StatsDto save(StatsDto statsDto);
}
