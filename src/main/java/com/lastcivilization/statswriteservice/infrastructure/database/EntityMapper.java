package com.lastcivilization.statswriteservice.infrastructure.database;

import com.lastcivilization.statswriteservice.domain.StatsValue;
import com.lastcivilization.statswriteservice.domain.dto.StatsDto;
import com.lastcivilization.statswriteservice.domain.dto.StatsValueDto;
import com.lastcivilization.statswriteservice.domain.dto.TimeBonusDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
interface EntityMapper {

    EntityMapper MAPPER = Mappers.getMapper(EntityMapper.class);

    StatsDto toDto(StatsEntity statsEntity);

    StatsEntity toEntity(StatsDto statsDto);

    default StatsValueDto toDto(StatsValueEntity statsValueEntity){
        return new StatsValueDto(
                statsValueEntity.getId(),
                statsValueEntity.getAmount(),
                toDto(statsValueEntity.getTimeBonus()),
                statsValueEntity.getType()
        );
    }

    TimeBonusDto toDto(TimeBonusEntity timeBonusEntity);
}
