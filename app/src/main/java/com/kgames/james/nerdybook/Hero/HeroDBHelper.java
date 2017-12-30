package com.kgames.james.nerdybook.Hero;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.kgames.james.nerdybook.Hero.DatabaseContract.HeroEntry.COLUMN_CURRENT_CHAPTER;
import static com.kgames.james.nerdybook.Hero.DatabaseContract.HeroEntry.COLUMN_ID;
import static com.kgames.james.nerdybook.Hero.DatabaseContract.HeroEntry.COLUMN_TOTAL_CHAPTERS;
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
                    DatabaseContract.HeroEntry.COLUMN_LUCK_CURRENT + " TEXT," +
                    DatabaseContract.HeroEntry.COLUMN_CURRENT_CHAPTER + " TEXT," +
                    DatabaseContract.HeroEntry.COLUMN_TOTAL_CHAPTERS + " TEXT);";

    public HeroDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public HeroDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public Cursor getAllHeroes() {
        SQLiteDatabase db = getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
    }

    public void updateChapters(String heroID, String currentChapter, String totalChapters) {
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_CURRENT_CHAPTER, currentChapter);
        contentValues.put(COLUMN_TOTAL_CHAPTERS, totalChapters);
        db.update(TABLE_NAME, contentValues, COLUMN_ID + " = ?", new String[]{heroID});
        db.close();
    }

    boolean deleteHero(int id) {
        SQLiteDatabase db = getWritableDatabase();
        return db.delete(TABLE_NAME, COLUMN_ID + "=?", new String[]{String.valueOf(id)}) == 1;
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