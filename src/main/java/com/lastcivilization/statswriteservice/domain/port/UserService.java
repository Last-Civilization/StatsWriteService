package com.lastcivilization.statswriteservice.domain.port;

import com.lastcivilization.statswriteservice.domain.port.dto.UserDto;

public interface UserService {

    UserDto getUser(String keycloakId);
}
