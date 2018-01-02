package com.kgames.james.nerdybook.Hero;

import android.provider.BaseColumns;

/**
 * Database Contract for Heroes.
 */

public class DatabaseContract {

    private DatabaseContract() {}

    public static class HeroEntry implements BaseColumns {
        public static final String TABLE_NAME = "Hero";
        public static final String COLUMN_ID = "ID";

        public static final String COLUMN_ADVENTURE = "adventure";
        public static final String COLUMN_DIFFICULTY = "difficulty";

        public static final String COLUMN_PLAYER_NAME = "name";

        public static final String COLUMN_ABILITY_MAX = "abilityMax";
        public static final String COLUMN_ABILITY_CURRENT = "abilityCurrent";
        public static final String COLUMN_STAMINA_MAX = "staminaMax";
        public static final String COLUMN_STAMINA_CURRENT = "staminaCurrent";
        public static final String COLUMN_LUCK_MAX = "luckMax";
        public static final String COLUMN_LUCK_CURRENT = "luckCurrent";

        public static final String COLUMN_ABILITY_POTIONS = "abilityPotions";
        public static final String COLUMN_STAMINA_POTIONS = "staminaPotions";
        public static final String COLUMN_LUCK_POTIONS = "luckPotions";

        public static final String COLUMN_TORCHS = "torchs";
        public static final String COLUMN_GC = "goldenCoins";
        public static final String COLUMN_MEALS = "meals";

        public static final String COLUMN_STUFF1 = "stuff1";
        public static final String COLUMN_STUFF2 = "stuff2";
        public static final String COLUMN_STUFF3 = "stuff3";

        public static final String COLUMN_CURRENT_CHAPTER = "currentChapter";
        public static final String COLUMN_TOTAL_CHAPTERS = "totalChapters";
    }

    public static final String SQL_DELETE_HERO =
            "DROP TABLE IF EXISTS " + HeroEntry.TABLE_NAME;

}
