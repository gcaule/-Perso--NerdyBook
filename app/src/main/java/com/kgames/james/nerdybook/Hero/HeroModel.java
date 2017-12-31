package com.kgames.james.nerdybook.Hero;

/**
 * Created by james on 17/12/17.
 */

public class HeroModel extends DatabaseContract.HeroEntry {

    private String ID;

    private String adventure;
    private String difficulty;

    private String name;

    private int abilityMax;
    private int abilityCurrent;
    private int staminaMax;
    private int staminaCurrent;
    private int luckMax;
    private int luckCurrent;

    private int currentChapter;
    private int totalChapters;


    public HeroModel() { }

    public HeroModel(String ID, String adventure, String difficulty,
                     String name, int abilityMax,
                     int abilityCurrent, int staminaMax,
                     int staminaCurrent, int luckMax, int luckCurrent,
                     int currentChapter, int totalChapters) {
        this.ID = ID;
        this.adventure = adventure;
        this.difficulty = difficulty;
        this.name = name;
        this.abilityMax = abilityMax;
        this.abilityCurrent = abilityCurrent;
        this.staminaMax = staminaMax;
        this.staminaCurrent = staminaCurrent;
        this.luckMax = luckMax;
        this.luckCurrent = luckCurrent;
        this.currentChapter = currentChapter;
        this.totalChapters = totalChapters;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }


    public String getAdventure() {
        return adventure;
    }

    public void setAdventure(String adventure) {
        this.adventure = adventure;
    }


    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public int getAbilityMax() {
        return abilityMax;
    }

    public void setAbilityMax(int abilityMax) {
        this.abilityMax = abilityMax;
    }


    public int getAbilityCurrent() {
        return abilityCurrent;
    }

    public void setAbilityCurrent(int abilityCurrent) {
        this.abilityCurrent = abilityCurrent;
    }


    public int getStaminaMax() {
        return staminaMax;
    }

    public void setStaminaMax(int staminaMax) {
        this.staminaMax = staminaMax;
    }


    public int getStaminaCurrent() {
        return staminaCurrent;
    }

    public void setStaminaCurrent(int staminaCurrent) {
        this.staminaCurrent = staminaCurrent;
    }


    public int getLuckMax() {
        return luckMax;
    }

    public void setLuckMax(int luckMax) {
        this.luckMax = luckMax;
    }


    public int getLuckCurrent() {
        return luckCurrent;
    }

    public void setLuckCurrent(int luckCurrent) {
        this.luckCurrent = luckCurrent;
    }


    public int getCurrentChapter() {
        return currentChapter;
    }

    public void setCurrentChapter(int currentChapter) {
        this.currentChapter = currentChapter;
    }


    public int getTotalChapters() {
        return totalChapters;
    }

    public void setTotalChapters(int totalChapters) {
        this.totalChapters = totalChapters;
    }

}
