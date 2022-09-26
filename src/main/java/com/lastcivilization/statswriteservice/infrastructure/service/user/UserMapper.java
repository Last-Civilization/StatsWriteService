package com.lastcivilization.statswriteservice.infrastructure.service.user;

import com.lastcivilization.statswriteservice.domain.port.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
interface UserMapper {

    UserMapper MAPPER = Mappers.getMapper(UserMapper.class);

    UserDto toDto(User user);
}
