package com.kgames.james.nerdybook.Hero;

/**
 * Created by james on 17/12/17.
 */

public class HeroModel extends DatabaseContract.HeroEntry {

    private String ID;

    private String adventure;
    private String difficulty;

    private String name;
    private String gender;

    private String abilityMax;
    private String abilityCurrent;
    private String staminaMax;
    private String staminaCurrent;
    private String luckMax;
    private String luckCurrent;

    private String currentChapter;
    private String totalChapters;


    public HeroModel() { }

    public HeroModel(String ID, String adventure, String difficulty,
                     String name, String gender, String abilityMax,
                     String abilityCurrent, String staminaMax,
                     String staminaCurrent, String luckMax, String luckCurrent,
                     String currentChapter, String totalChapters) {
        this.ID = ID;
        this.adventure = adventure;
        this.difficulty = difficulty;
        this.name = name;
        this.gender = gender;
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


    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }


    public String getAbilityMax() {
        return abilityMax;
    }

    public void setAbilityMax(String abilityMax) {
        this.abilityMax = abilityMax;
    }


    public String getAbilityCurrent() {
        return abilityCurrent;
    }

    public void setAbilityCurrent(String abilityCurrent) {
        this.abilityCurrent = abilityCurrent;
    }


    public String getStaminaMax() {
        return staminaMax;
    }

    public void setStaminaMax(String staminaMax) {
        this.staminaMax = staminaMax;
    }


    public String getStaminaCurrent() {
        return staminaCurrent;
    }

    public void setStaminaCurrent(String staminaCurrent) {
        this.staminaCurrent = staminaCurrent;
    }


    public String getLuckMax() {
        return luckMax;
    }

    public void setLuckMax(String luckMax) {
        this.luckMax = luckMax;
    }


    public String getLuckCurrent() {
        return luckCurrent;
    }

    public void setLuckCurrent(String luckCurrent) {
        this.luckCurrent = luckCurrent;
    }


    public String getCurrentChapter() {
        return currentChapter;
    }

    public void setCurrentChapter(String currentChapter) {
        this.currentChapter = currentChapter;
    }


    public String getTotalChapters() {
        return totalChapters;
    }

    public void setTotalChapters(String totalChapters) {
        this.totalChapters = totalChapters;
    }

}
