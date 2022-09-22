package com.lastcivilization.statswriteservice.infrastructure.application.rest;

import com.lastcivilization.statswriteservice.utils.IntegrationBaseClass;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class StatsControllerTest extends IntegrationBaseClass {

    @Test
    void shouldCreateStats() throws Exception {
        //given
        //when
        ResultActions createResult = mockMvc.perform(post("/stats"));
        //then
        createResult.andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.lvl.current").value(1))
                .andExpect(jsonPath("$.lvl.experience").value(0))
                .andExpect(jsonPath("$.damage.amount").value(1))
                .andExpect(jsonPath("$.damage.type").value("DAMAGE"))
                .andExpect(jsonPath("$.damage.timeBonus.endDate").exists())
                .andExpect(jsonPath("$.strength.amount").value(1))
                .andExpect(jsonPath("$.strength.type").value("STRENGTH"))
                .andExpect(jsonPath("$.strength.timeBonus.endDate").exists())
                .andExpect(jsonPath("$.dexterity.amount").value(1))
                .andExpect(jsonPath("$.dexterity.type").value("DEXTERITY"))
                .andExpect(jsonPath("$.dexterity.timeBonus.endDate").exists())
                .andExpect(jsonPath("$.defense.amount").value(1))
                .andExpect(jsonPath("$.defense.type").value("DEFENSE"))
                .andExpect(jsonPath("$.defense.timeBonus.endDate").exists());
    }

    @Test
    void experienceUp() {
        //given
        //when
        //then
    }

    @Test
    void trainStrength() {
        //given
        //when
        //then
    }

    @Test
    void trainDexterity() {
        //given
        //when
        //then
    }

    @Test
    void giveTimeBonusToStrength() {
        //given
        //when
        //then
    }

    @Test
    void giveTimeBonusToDamage() {
        //given
        //when
        //then
    }

    @Test
    void giveTimeBonusToDexterity() {
        //given
        //when
        //then
    }

    @Test
    void giveTimeBonusToDefence() {
        //given
        //when
        //then
    }
}