package com.lastcivilization.statswriteservice.domain.dto;

public record UserDto (
        String keycloakId,
        Long stats,
        Long account
) {}
