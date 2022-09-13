package com.lastcivilization.statswriteservice.domain.dto;

import java.time.LocalDateTime;

public record TimeBonusDto(
        Long id,
        LocalDateTime endDate,
        int amount
) { }
