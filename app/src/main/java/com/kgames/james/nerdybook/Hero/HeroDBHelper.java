package com.kgames.james.nerdybook.Hero;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.kgames.james.nerdybook.Hero.DatabaseContract.HeroEntry.COLUMN_ID;
import static com.kgames.james.nerdybook.Hero.DatabaseContract.HeroEntry.TABLE_NAME;
import static com.kgames.james.nerdybook.Hero.DatabaseContract.SQL_DELETE_HERO;

/**
 * Created by apprenti on 03/10/17.
 */

public class HeroDBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Heroes.db";

    public static final String SQL_CREATE_HERO =
            "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
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

    public Cursor getAllHeroes() {
        SQLiteDatabase db = getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
    }

    boolean deleteHero(int id) {
        SQLiteDatabase db = getWritableDatabase();
        return db.delete(TABLE_NAME, COLUMN_ID + "=?", new String[]{String.valueOf(id)}) == 1;
    }

    boolean isTableEmpty() {

        SQLiteDatabase db = getReadableDatabase();
        Cursor mCursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        Boolean tableIsEmpty;

        if (mCursor.moveToFirst()) {
            tableIsEmpty = true;

        } else {
            tableIsEmpty = false;
        }
        return tableIsEmpty;
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