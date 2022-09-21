package com.lastcivilization.statswriteservice.domain.port;

import com.lastcivilization.statswriteservice.domain.dto.UserDto;
import com.lastcivilization.statswriteservice.domain.exception.NotEnoughMoneyException;

public interface UserService {

    UserDto getUser(String keycloakId);
}
