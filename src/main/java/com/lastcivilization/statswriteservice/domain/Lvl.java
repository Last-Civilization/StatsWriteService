package com.lastcivilization.statswriteservice.domain;

class Lvl {

    private Long id;
    private int current;
    private int experience;

    public Lvl(Long id, int current, int experience) {
        this.id = id;
        this.current = current;
        this.experience = experience;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public static final class Builder {

        private Long id;
        private int current;
        private int experience;

        private Builder() {
        }

        public static Builder aLvl() {
            return new Builder();
        }

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder current(int current) {
            this.current = current;
            return this;
        }

        public Builder experience(int experience) {
            this.experience = experience;
            return this;
        }

        public Lvl build() {
            return new Lvl(id, current, experience);
        }
    }
}