package com.lastcivilization.statswriteservice.domain.dto;

public record UserDto (
        String keycloakId,
        String login,
        String email,
        Long stats,
        Long equipment,
        int money
) {}
