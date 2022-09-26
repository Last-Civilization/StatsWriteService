package com.lastcivilization.statswriteservice.infrastructure.database;

import com.lastcivilization.statswriteservice.domain.view.StatsModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
interface EntityMapper {

    EntityMapper MAPPER = Mappers.getMapper(EntityMapper.class);

    StatsModel toDto(StatsEntity statsEntity);

    StatsEntity toEntity(StatsModel statsModel);
}
