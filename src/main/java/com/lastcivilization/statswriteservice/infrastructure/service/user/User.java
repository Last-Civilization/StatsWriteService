package com.lastcivilization.statswriteservice.infrastructure.service.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
record User (
        @JsonProperty("stats")
        Long stats,
        @JsonProperty("account")
        Long account
){ }
