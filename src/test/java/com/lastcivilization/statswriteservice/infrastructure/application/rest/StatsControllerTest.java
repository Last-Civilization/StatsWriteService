package com.lastcivilization.statswriteservice.infrastructure.application.rest;

import com.lastcivilization.statswriteservice.utils.IntegrationBaseClass;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class StatsControllerTest extends IntegrationBaseClass {

    @BeforeEach
    void restTestStats(){
        statsCreator.resetTestStatsDetails();
    }

    @Test
    void shouldCreateStats() throws Exception {
        //given
        //when
        ResultActions createResult = mockMvc.perform(post("/stats"));
        //then
        createResult.andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.health").value(100))
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
    void shouldExperienceUp() throws Exception {
        //given
        statsCreator.resetTestStatsDetails();
        //when
        ResultActions experienceUpResult = mockMvc.perform(put("/stats/1/experiences/100"));
        //then
        experienceUpResult.andExpect(status().isOk())
                .andExpect(jsonPath("$.health").value(100))
                .andExpect(jsonPath("$.lvl.current").value(1))
                .andExpect(jsonPath("$.lvl.experience").value(100));
    }

    @Test
    void shouldLvlUp() throws Exception {
        //given
        statsCreator.resetTestStatsDetails();
        //when
        ResultActions experienceUpResult = mockMvc.perform(put("/stats/1/experiences/101"));
        //then
        experienceUpResult.andExpect(status().isOk())
                .andExpect(jsonPath("$.health").value(120))
                .andExpect(jsonPath("$.lvl.current").value(2))
                .andExpect(jsonPath("$.lvl.experience").value(0));
    }

    @Test
    void shouldReturnStatsNotFoundStatusWhileExperienceUpping() throws Exception {
        //given
        //when
        ResultActions experienceUpResult = mockMvc.perform(put("/stats/2/experiences/100"));
        //then
        experienceUpResult.andExpect(status().isNotFound());
    }

    @Test
    void shouldReturnUserNotFoundStatusWhileExperienceUpping() throws Exception {
        //given
        //when
        ResultActions experienceUpResult = mockMvc.perform(put("/stats/3/experiences/100"));
        //then
        experienceUpResult.andExpect(status().isNotFound());
    }

    @Test
    void shouldTrainStrength() throws Exception {
        //given
        //when
        ResultActions trainStrengthResult = mockMvc.perform(put("/stats/1/strengths"));
        //then
        trainStrengthResult.andExpect(status().isOk())
                .andExpect(jsonPath("[0].amount").value(2))
                .andExpect(jsonPath("[0].type").value("STRENGTH"))
                .andExpect(jsonPath("[1].amount").value(2))
                .andExpect(jsonPath("[1].type").value("DAMAGE"));
    }

    @Test
    void shouldReturnStatsNotFoundStatusWhileTrainingStrength() throws Exception {
        //given
        //when
        ResultActions experienceUpResult = mockMvc.perform(put("/stats/2/strengths"));
        //then
        experienceUpResult.andExpect(status().isNotFound());
    }

    @Test
    void shouldReturnUserNotFoundStatusWhileTrainingStrength() throws Exception {
        //given
        //when
        ResultActions experienceUpResult = mockMvc.perform(put("/stats/3/strengths"));
        //then
        experienceUpResult.andExpect(status().isNotFound());
    }

    @Test
    void shouldTrainDexterity() throws Exception {
        //given
        //when
        ResultActions trainStrengthResult = mockMvc.perform(put("/stats/1/dexterity"));
        //then
        trainStrengthResult.andExpect(status().isOk())
                .andExpect(jsonPath("$.amount").value(2))
                .andExpect(jsonPath("$.type").value("DEXTERITY"));
    }

    @Test
    void shouldReturnStatsNotFoundStatusWhileTrainingDexterity() throws Exception {
        //given
        //when
        ResultActions experienceUpResult = mockMvc.perform(put("/stats/2/dexterity"));
        //then
        experienceUpResult.andExpect(status().isNotFound());
    }

    @Test
    void shouldReturnUserNotFoundStatusWhileTrainingDexterity() throws Exception {
        //given
        //when
        ResultActions experienceUpResult = mockMvc.perform(put("/stats/3/dexterity"));
        //then
        experienceUpResult.andExpect(status().isNotFound());
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