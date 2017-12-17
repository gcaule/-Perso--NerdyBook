package com.kgames.james.nerdybook.Hero;

import android.provider.BaseColumns;

/**
 * Created by apprenti on 03/10/17.
 */

public class DatabaseContract {

    private DatabaseContract() {}

    public static class HeroEntry implements BaseColumns {
        public static final String TABLE_NAME = "Hero";
        public static final String COLUMN_ID = "id";

        public static final String COLUMN_ADVENTURE = "adventure";
        public static final String COLUMN_DIFFICULTY = "difficulty";

        public static final String COLUMN_PLAYER_NAME = "name";
        public static final String COLUMN_PLAYER_GENDER = "gender";

        public static final String COLUMN_ABILITY_MAX = "abilityMax";
        public static final String COLUMN_ABILITY_CURRENT = "abilityCurrent";
        public static final String COLUMN_STAMINA_MAX = "staminaMax";
        public static final String COLUMN_STAMINA_CURRENT = "staminaCurrent";
        public static final String COLUMN_LUCK_MAX = "luckMax";
        public static final String COLUMN_LUCK_CURRENT = "luckCurrent";
    }

    public static final String SQL_DELETE_HERO =
            "DROP TABLE IF EXISTS " + HeroEntry.TABLE_NAME;

}