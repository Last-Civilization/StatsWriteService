package com.lastcivilization.statswriteservice;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

class StatsWriteServiceApplicationTests {

    @Test
    void contextLoads() {
        int lvl = 1;
        System.out.println(lvl +":" +getExp(lvl));
        lvl = 2;
        System.out.println(lvl +":" +getExp(lvl));
        lvl = 3;
        System.out.println(lvl +":" +getExp(lvl));
        lvl = 4;
        System.out.println(lvl +":" +getExp(lvl));
        lvl = 5;
        System.out.println(lvl +":" +getExp(lvl));
        lvl = 23;
        System.out.println(lvl +":" +getExp(lvl));
        lvl = 32;
        System.out.println(lvl +":" +getExp(lvl));
        lvl = 54;
        System.out.println(lvl +":" +getExp(lvl));
        lvl = 67;
        System.out.println(lvl +":" +getExp(lvl));
    }

    private int getExp(int lvl) {
        return ((lvl + 100) * (lvl / 2)) + 100;
    }

}
