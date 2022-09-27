package com.lastcivilization.statswriteservice.infrastructure.application.rest;

import com.lastcivilization.statswriteservice.utils.IntegrationBaseClass;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class StatsControllerTest extends IntegrationBaseClass {

    @Test
    void shouldCreateStats() throws Exception {
        //Addn
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
        //Addn
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
        //Addn
        //when
        ResultActions experienceUpResult = mockMvc.perform(put("/stats/1/experiences/101"));
        //then
        experienceUpResult.andExpect(status().isOk())
                .andExpect(jsonPath("$.health").value(120))
                .andExpect(jsonPath("$.lvl.current").value(2))
                .andExpect(jsonPath("$.lvl.experience").value(0));
    }

    @Test
    void shouldReturnValidationExceptionStatusWhileExperienceUpping() throws Exception {
        //Addn
        //when
        ResultActions experienceUpResult = mockMvc.perform(put("/stats/1/experiences/0"));
        //then
        experienceUpResult.andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturnStatsNotFoundStatusWhileExperienceUpping() throws Exception {
        //Addn
        //when
        ResultActions experienceUpResult = mockMvc.perform(put("/stats/2/experiences/100"));
        //then
        experienceUpResult.andExpect(status().isNotFound());
    }

    @Test
    void shouldReturnUserNotFoundStatusWhileExperienceUpping() throws Exception {
        //Addn
        //when
        ResultActions experienceUpResult = mockMvc.perform(put("/stats/3/experiences/100"));
        //then
        experienceUpResult.andExpect(status().isNotFound());
    }

    @Test
    void shouldTrainStrength() throws Exception {
        //Addn
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
        //Addn
        //when
        ResultActions experienceUpResult = mockMvc.perform(put("/stats/2/strengths"));
        //then
        experienceUpResult.andExpect(status().isNotFound());
    }

    @Test
    void shouldReturnUserNotFoundStatusWhileTrainingStrength() throws Exception {
        //Addn
        //when
        ResultActions experienceUpResult = mockMvc.perform(put("/stats/3/strengths"));
        //then
        experienceUpResult.andExpect(status().isNotFound());
    }

    @Test
    void shouldTrainDexterity() throws Exception {
        //Addn
        //when
        ResultActions trainStrengthResult = mockMvc.perform(put("/stats/1/dexterity"));
        //then
        trainStrengthResult.andExpect(status().isOk())
                .andExpect(jsonPath("$.amount").value(2))
                .andExpect(jsonPath("$.type").value("DEXTERITY"));
    }

    @Test
    void shouldReturnStatsNotFoundStatusWhileTrainingDexterity() throws Exception {
        //Addn
        //when
        ResultActions experienceUpResult = mockMvc.perform(put("/stats/2/dexterity"));
        //then
        experienceUpResult.andExpect(status().isNotFound());
    }

    @Test
    void shouldReturnUserNotFoundStatusWhileTrainingDexterity() throws Exception {
        //Addn
        //when
        ResultActions experienceUpResult = mockMvc.perform(put("/stats/3/dexterity"));
        //then
        experienceUpResult.andExpect(status().isNotFound());
    }

    @Test
    void shouldAddTimeBonusToStrength() throws Exception {
        //Addn
        //when
        ResultActions addTimeBonusResult = mockMvc.perform(put("/stats/1/strengths/bonuses/1/times/1"));
        //then
        addTimeBonusResult.andExpect(status().isOk())
                .andExpect(jsonPath("$.timeBonus.endDate").exists())
                .andExpect(jsonPath("$.timeBonus.amount").value(1))
                .andExpect(jsonPath("$.total").value(1));
    }

    @Test
    void shouldReturnValidationExceptionStatusWhileAddingTimeBonusToStrength() throws Exception {
        //Addn
        //when
        ResultActions experienceUpResult = mockMvc.perform(put("/stats/1/strengths/bonuses/0/times/0"));
        //then
        experienceUpResult.andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturnStatsNotFoundStatusWhileAddingTimeBonusToStrength() throws Exception {
        //Addn
        //when
        ResultActions experienceUpResult = mockMvc.perform(put("/stats/2/strengths/bonuses/1/times/1"));
        //then
        experienceUpResult.andExpect(status().isNotFound());
    }

    @Test
    void shouldReturnUserNotFoundStatusWhileAddingTimeBonusToStrength() throws Exception {
        //Addn
        //when
        ResultActions experienceUpResult = mockMvc.perform(put("/stats/3/strengths/bonuses/1/times/1"));
        //then
        experienceUpResult.andExpect(status().isNotFound());
    }

    @Test
    void shouldAddTimeBonusToDamage() throws Exception {
        //Addn
        //when
        ResultActions addTimeBonusResult = mockMvc.perform(put("/stats/1/damages/bonuses/1/times/1"));
        //then
        addTimeBonusResult.andExpect(status().isOk())
                .andExpect(jsonPath("$.timeBonus.endDate").exists())
                .andExpect(jsonPath("$.timeBonus.amount").value(1))
                .andExpect(jsonPath("$.total").value(1));
    }

    @Test
    void shouldReturnValidationExceptionStatusWhileAddingTimeBonusToDamage() throws Exception {
        //Addn
        //when
        ResultActions experienceUpResult = mockMvc.perform(put("/stats/1/damages/bonuses/0/times/0"));
        //then
        experienceUpResult.andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturnStatsNotFoundStatusWhileAddingTimeBonusToDamage() throws Exception {
        //Addn
        //when
        ResultActions experienceUpResult = mockMvc.perform(put("/stats/2/damages/bonuses/1/times/1"));
        //then
        experienceUpResult.andExpect(status().isNotFound());
    }

    @Test
    void shouldReturnUserNotFoundStatusWhileAddingTimeBonusToDamage() throws Exception {
        //Addn
        //when
        ResultActions experienceUpResult = mockMvc.perform(put("/stats/3/damages/bonuses/1/times/1"));
        //then
        experienceUpResult.andExpect(status().isNotFound());
    }

    @Test
    void shouldAddTimeBonusToDexterity() throws Exception {
        //Addn
        //when
        ResultActions addTimeBonusResult = mockMvc.perform(put("/stats/1/dexterity/bonuses/1/times/1"));
        //then
        addTimeBonusResult.andExpect(status().isOk())
                .andExpect(jsonPath("$.timeBonus.endDate").exists())
                .andExpect(jsonPath("$.timeBonus.amount").value(1))
                .andExpect(jsonPath("$.total").value(1));
    }

    @Test
    void shouldReturnValidationExceptionStatusWhileAddingTimeBonusToDexterity() throws Exception {
        //Addn
        //when
        ResultActions experienceUpResult = mockMvc.perform(put("/stats/1/dexterity/bonuses/0/times/0"));
        //then
        experienceUpResult.andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturnStatsNotFoundStatusWhileAddingTimeBonusToDexterity() throws Exception {
        //Addn
        //when
        ResultActions experienceUpResult = mockMvc.perform(put("/stats/2/dexterity/bonuses/1/times/1"));
        //then
        experienceUpResult.andExpect(status().isNotFound());
    }

    @Test
    void shouldReturnUserNotFoundStatusWhileAddingTimeBonusToDexterity() throws Exception {
        //Addn
        //when
        ResultActions experienceUpResult = mockMvc.perform(put("/stats/3/dexterity/bonuses/1/times/1"));
        //then
        experienceUpResult.andExpect(status().isNotFound());
    }

    @Test
    void shouldAddTimeBonusToDefence() throws Exception {
        //Addn
        //when
        ResultActions addTimeBonusResult = mockMvc.perform(put("/stats/1/defences/bonuses/1/times/1"));
        //then
        addTimeBonusResult.andExpect(status().isOk())
                .andExpect(jsonPath("$.timeBonus.endDate").exists())
                .andExpect(jsonPath("$.timeBonus.amount").value(1))
                .andExpect(jsonPath("$.total").value(1));
    }

    @Test
    void shouldReturnValidationExceptionStatusWhileAddingTimeBonusToDefence() throws Exception {
        //Addn
        //when
        ResultActions experienceUpResult = mockMvc.perform(put("/stats/1/defences/bonuses/0/times/0"));
        //then
        experienceUpResult.andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturnStatsNotFoundStatusWhileAddingTimeBonusToDefence() throws Exception {
        //Addn
        //when
        ResultActions experienceUpResult = mockMvc.perform(put("/stats/2/defences/bonuses/1/times/1"));
        //then
        experienceUpResult.andExpect(status().isNotFound());
    }

    @Test
    void shouldReturnUserNotFoundStatusWhileAddingTimeBonusToDefence() throws Exception {
        //Addn
        //when
        ResultActions experienceUpResult = mockMvc.perform(put("/stats/3/defences/bonuses/1/times/1"));
        //then
        experienceUpResult.andExpect(status().isNotFound());
    }
}