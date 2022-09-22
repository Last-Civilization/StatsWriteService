package com.lastcivilization.statswriteservice.infrastructure.service.user;

import com.lastcivilization.statswriteservice.domain.dto.UserDto;
import com.lastcivilization.statswriteservice.domain.port.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.lastcivilization.statswriteservice.infrastructure.service.user.UserMapper.MAPPER;

@Service
@RequiredArgsConstructor
class UserServiceAdapter implements UserService {

    private final UserClient userClient;

    @Override
    public UserDto getUser(String keycloakId) {
        User user = userClient.getUser(keycloakId);
        return MAPPER.toDto(user);
    }
}
