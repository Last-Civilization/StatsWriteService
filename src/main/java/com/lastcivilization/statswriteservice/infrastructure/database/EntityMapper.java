package com.lastcivilization.statswriteservice.infrastructure.database;

import com.lastcivilization.statswriteservice.domain.dto.LvlDto;
import com.lastcivilization.statswriteservice.domain.dto.StatsDto;
import com.lastcivilization.statswriteservice.domain.dto.StatsValueDto;
import com.lastcivilization.statswriteservice.domain.dto.TimeBonusDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
interface EntityMapper {

    EntityMapper MAPPER = Mappers.getMapper(EntityMapper.class);

    StatsDto toDto(StatsEntity statsEntity);
    StatsValueDto toDto(StatsValueEntity statsEntity);
    LvlDto toDto(LvlEntity statsEntity);
    TimeBonusDto toDto(TimeBonusEntity statsEntity);

    StatsEntity toEntity(StatsDto statsDto);
    StatsValueDto toEntity(StatsValueDto statsValueDto);
    LvlDto toEntity(LvlDto lvlDto);
    TimeBonusDto toEntity(TimeBonusDto timeBonusDto);
}
