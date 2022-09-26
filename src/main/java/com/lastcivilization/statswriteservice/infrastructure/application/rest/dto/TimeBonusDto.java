package com.lastcivilization.statswriteservice.infrastructure.application.rest.dto;

import java.time.LocalDateTime;

public record TimeBonusDto(
        Long id,
        LocalDateTime endDate,
        int amount
) { }
