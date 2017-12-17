package com.kgames.james.nerdybook.Hero;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.kgames.james.nerdybook.Hero.DatabaseContract.SQL_DELETE_HERO;

/**
 * Created by apprenti on 03/10/17.
 */

public class HeroDBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Heroes.db";

    public static final String SQL_CREATE_HERO =
            "CREATE TABLE IF NOT EXISTS " + DatabaseContract.HeroEntry.TABLE_NAME + " (" +
                    DatabaseContract.HeroEntry.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    DatabaseContract.HeroEntry.COLUMN_ADVENTURE + " TEXT," +
                    DatabaseContract.HeroEntry.COLUMN_DIFFICULTY + " TEXT," +
                    DatabaseContract.HeroEntry.COLUMN_PLAYER_NAME + " TEXT," +
                    DatabaseContract.HeroEntry.COLUMN_PLAYER_GENDER + " TEXT," +
                    DatabaseContract.HeroEntry.COLUMN_ABILITY_MAX + " TEXT," +
                    DatabaseContract.HeroEntry.COLUMN_ABILITY_CURRENT + " TEXT," +
                    DatabaseContract.HeroEntry.COLUMN_STAMINA_MAX + " TEXT," +
                    DatabaseContract.HeroEntry.COLUMN_STAMINA_CURRENT + " TEXT," +
                    DatabaseContract.HeroEntry.COLUMN_LUCK_MAX + " TEXT," +
                    DatabaseContract.HeroEntry.COLUMN_LUCK_CURRENT + " TEXT);";

    public HeroDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_HERO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_HERO);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

}