package com.lastcivilization.statswriteservice.infrastructure.database;

import org.springframework.data.jpa.repository.JpaRepository;

interface StatsJpaRepository extends JpaRepository<StatsEntity, Long> { }
