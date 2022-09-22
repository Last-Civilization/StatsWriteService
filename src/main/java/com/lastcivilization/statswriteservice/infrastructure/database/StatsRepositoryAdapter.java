package com.lastcivilization.statswriteservice.infrastructure.database;

import com.lastcivilization.statswriteservice.domain.dto.StatsDto;
import com.lastcivilization.statswriteservice.domain.port.StatsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.lastcivilization.statswriteservice.infrastructure.database.EntityMapper.MAPPER;

@Service
@RequiredArgsConstructor
class StatsRepositoryAdapter implements StatsRepository {

    private final StatsJpaRepository statsJpaRepository;

    @Override
    public Optional<StatsDto> findById(Long id) {
        Optional<StatsEntity> statsEntity = statsJpaRepository.findById(id);
        return statsEntity
                .map(MAPPER::toDto);
    }

    @Override
    public StatsDto save(StatsDto statsDto) {
        StatsEntity statsEntity = MAPPER.toEntity(statsDto);
        StatsEntity savedStatsEntity = statsJpaRepository.save(statsEntity);
        return MAPPER.toDto(savedStatsEntity);
    }
}
