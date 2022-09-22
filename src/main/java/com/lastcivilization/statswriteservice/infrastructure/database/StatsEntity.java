package com.lastcivilization.statswriteservice.infrastructure.database;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "stats")
class StatsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name = "lvl", referencedColumnName = "id")
    private LvlEntity lvl;
    @OneToOne
    @JoinColumn(name = "damage", referencedColumnName = "id")
    private StatsValueEntity damage;
    @OneToOne
    @JoinColumn(name = "strength", referencedColumnName = "id")
    private StatsValueEntity strength;
    @OneToOne
    @JoinColumn(name = "dexterity", referencedColumnName = "id")
    private StatsValueEntity dexterity;
    @OneToOne
    @JoinColumn(name = "defense", referencedColumnName = "id")
    private StatsValueEntity defense;
}
