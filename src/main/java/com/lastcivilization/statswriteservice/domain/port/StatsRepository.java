package com.lastcivilization.statswriteservice.domain.port;

import com.lastcivilization.statswriteservice.domain.dto.StatsDto;

import java.util.Optional;

public interface StatsRepository {

    Optional<StatsDto> findById(Long id);
    StatsDto save(StatsDto statsDto);
}
