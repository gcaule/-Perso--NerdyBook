package com.kgames.james.nerdybook.Hero;

/**
 * Model for Heroes.
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

    private int abilityPotions;
    private int staminaPotions;
    private int luckPotions;

    private int torchs;
    private int goldenCoins;
    private int meals;

    private String stuff1;
    private String stuff2;
    private String stuff3;

    private int currentChapter;
    private int totalChapters;


    public HeroModel() { }

    public HeroModel(String ID, String adventure, String difficulty,
                     String name, int abilityMax,
                     int abilityCurrent, int staminaMax,
                     int staminaCurrent, int luckMax, int luckCurrent,
                     int abilityPotions, int staminaPotions,
                     int luckPotions, int torchs, int goldenCoins, int meals,
                     String stuff1, String stuff2, String stuff3,
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
        this.abilityPotions = abilityPotions;
        this.staminaPotions = staminaPotions;
        this.luckPotions = luckPotions;
        this.torchs = torchs;
        this.goldenCoins = goldenCoins;
        this.meals = meals;
        this.stuff1 = stuff1;
        this.stuff2 = stuff2;
        this.stuff3 = stuff3;
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


    public int getAbilityPotions() {
        return abilityPotions;
    }

    public void setAbilityPotions(int abilityPotions) {
        this.abilityPotions = abilityPotions;
    }


    public int getStaminaPotions() {
        return staminaPotions;
    }

    public void setStaminaPotions(int staminaPotions) {
        this.staminaPotions = staminaPotions;
    }


    public int getLuckPotions() {
        return luckPotions;
    }

    public void setLuckPotions(int luckPotions) {
        this.luckPotions = luckPotions;
    }


    public int getTorchs() {
        return torchs;
    }

    public void setTorchs(int torchs) {
        this.torchs = torchs;
    }


    public int getGoldenCoins() {
        return goldenCoins;
    }

    public void setGoldenCoins(int goldenCoins) {
        this.goldenCoins = goldenCoins;
    }


    public int getMeals() {
        return meals;
    }

    public void setMeals(int meals) {
        this.meals = meals;
    }


    public String getStuff1() {
        return stuff1;
    }

    public void setStuff1(String stuff1) {
        this.stuff1 = stuff1;
    }


    public String getStuff2() {
        return stuff2;
    }

    public void setStuff2(String stuff2) {
        this.stuff2 = stuff2;
    }


    public String getStuff3() {
        return stuff3;
    }

    public void setStuff3(String stuff3) {
        this.stuff3 = stuff3;
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
