package com.lastcivilization.statswriteservice.infrastructure.service.user;

import com.lastcivilization.statswriteservice.domain.port.dto.UserDto;
import com.lastcivilization.statswriteservice.domain.exception.ApplicationException;
import com.lastcivilization.statswriteservice.domain.exception.UserNotFoundException;
import com.lastcivilization.statswriteservice.domain.port.UserService;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.lastcivilization.statswriteservice.infrastructure.service.user.UserMapper.MAPPER;

@Service
@RequiredArgsConstructor
class UserServiceAdapter implements UserService {

    private final UserClient userClient;

    @Override
    public UserDto getUser(String keycloakId) {
        try {
            User user = userClient.getUser(keycloakId);
            return MAPPER.toDto(user);
        } catch (FeignException exception){
            if(exception.status() == 404){
                throw new UserNotFoundException(keycloakId);
            }
            throw new ApplicationException(exception.getMessage());
        }
    }
}
