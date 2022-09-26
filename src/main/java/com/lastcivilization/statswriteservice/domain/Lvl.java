package com.lastcivilization.statswriteservice.domain;

class Lvl {

    private Long id;
    private int current;
    private int experience;

    Lvl(Long id, int current, int experience) {
        this.id = id;
        this.current = current;
        this.experience = experience;
    }

    Long getId() {
        return id;
    }

    void setId(Long id) {
        this.id = id;
    }

    int getCurrent() {
        return current;
    }

    void setCurrent(int current) {
        this.current = current;
    }

    int getExperience() {
        return experience;
    }

    void setExperience(int experience) {
        this.experience = experience;
    }

    static final class Builder {

        private Long id;
        private int current = 1;
        private int experience;

        private Builder() {
        }

        static Builder aLvl() {
            return new Builder();
        }

        Builder id(Long id) {
            this.id = id;
            return this;
        }

        Builder current(int current) {
            this.current = current;
            return this;
        }

        Builder experience(int experience) {
            this.experience = experience;
            return this;
        }

        Lvl build() {
            return new Lvl(id, current, experience);
        }
    }
}
