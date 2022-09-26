package com.lastcivilization.statswriteservice.domain.view;

import java.time.LocalDateTime;

public record TimeBonusModel(
        Long id,
        LocalDateTime endDate,
        int amount
) { }
