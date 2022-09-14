package com.lastcivilization.statswriteservice;

import org.junit.jupiter.api.Test;

class BalanceTests {
    int lvl = 1;
    @Test
    void testExperienceOnLvlNeed() {
        for(int x = 0; x < 50 ; x++){
            initUpdateLvl();
        }
    }

    private void initUpdateLvl() {
        System.out.println(lvl +":" +getExp(lvl));
    }

    private int getExp(int lvl) {
        return ((lvl + 100) * (lvl / 2)) + 100;
    }

    int currentDamage = 1;
    int currentStrength = 1;

    @Test
    void testStrengthTrain(){
        for(int x = 0; x < 50 ; x++){
            initUpdateTrain();
        }
    }

    private void initUpdateTrain() {
        System.out.println("BEFORE: S:" + currentStrength + "D:" + currentDamage);
        currentStrength++;
        currentDamage += getUpgrade(currentStrength);
        System.out.println("AFTER: S:" + currentStrength + "D:" + currentDamage);
    }

    private int getUpgrade(int currentStrength) {
        return ((currentStrength * 3) / 15) + 1;
    }

    int toTrain = 1;

    @Test
    void testMoneyOnTrainNeed(){
        for (int x = 0; x < 50; x++){
            initTrain();
        }
    }

    private void initTrain() {
        System.out.println(toTrain + "->" + getNextLvlCost());
        toTrain++;
    }

    private int getNextLvlCost() {
        return (toTrain * 10) / 2;
    }
}
