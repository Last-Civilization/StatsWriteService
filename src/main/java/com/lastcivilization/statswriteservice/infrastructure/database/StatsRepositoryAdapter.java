package com.lastcivilization.statswriteservice.infrastructure.database;

import com.lastcivilization.statswriteservice.domain.view.StatsModel;
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
    public Optional<StatsModel> findById(Long id) {
        Optional<StatsEntity> statsEntity = statsJpaRepository.findById(id);
        return statsEntity
                .map(MAPPER::toDto);
    }

    @Override
    public StatsModel save(StatsModel statsModel) {
        StatsEntity statsEntity = MAPPER.toEntity(statsModel);
        StatsEntity savedStatsEntity = statsJpaRepository.save(statsEntity);
        return MAPPER.toDto(savedStatsEntity);
    }

    @Override
    public void deleteById(long id) {
        statsJpaRepository.deleteById(id);
    }
}
