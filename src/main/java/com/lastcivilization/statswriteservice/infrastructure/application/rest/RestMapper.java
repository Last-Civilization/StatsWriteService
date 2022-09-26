package com.lastcivilization.statswriteservice.infrastructure.application.rest;

import com.lastcivilization.statswriteservice.domain.view.StatsModel;
import com.lastcivilization.statswriteservice.domain.view.StatsValueModel;
import com.lastcivilization.statswriteservice.domain.view.TimeBonusModel;
import com.lastcivilization.statswriteservice.infrastructure.application.rest.dto.StatsDto;
import com.lastcivilization.statswriteservice.infrastructure.application.rest.dto.StatsValueDto;
import com.lastcivilization.statswriteservice.infrastructure.application.rest.dto.TimeBonusDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
interface RestMapper {

    RestMapper MAPPER = Mappers.getMapper(RestMapper.class);

    StatsDto toDto(StatsModel statsModel);

    default StatsValueDto toDto(StatsValueModel statsValueModel){
        return new StatsValueDto(
                statsValueModel.id(),
                statsValueModel.amount(),
                toDto(statsValueModel.timeBonus()),
                statsValueModel.type()
        );
    }

    TimeBonusDto toDto(TimeBonusModel timeBonus);
}
