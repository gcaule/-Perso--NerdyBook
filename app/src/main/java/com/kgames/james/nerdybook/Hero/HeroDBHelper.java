package com.kgames.james.nerdybook.Hero;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.kgames.james.nerdybook.R;

import static com.kgames.james.nerdybook.Hero.DatabaseContract.HeroEntry.COLUMN_ABILITY_CURRENT;
import static com.kgames.james.nerdybook.Hero.DatabaseContract.HeroEntry.COLUMN_ABILITY_MAX;
import static com.kgames.james.nerdybook.Hero.DatabaseContract.HeroEntry.COLUMN_CURRENT_CHAPTER;
import static com.kgames.james.nerdybook.Hero.DatabaseContract.HeroEntry.COLUMN_GC;
import static com.kgames.james.nerdybook.Hero.DatabaseContract.HeroEntry.COLUMN_ID;
import static com.kgames.james.nerdybook.Hero.DatabaseContract.HeroEntry.COLUMN_LUCK_CURRENT;
import static com.kgames.james.nerdybook.Hero.DatabaseContract.HeroEntry.COLUMN_LUCK_MAX;
import static com.kgames.james.nerdybook.Hero.DatabaseContract.HeroEntry.COLUMN_STAMINA_CURRENT;
import static com.kgames.james.nerdybook.Hero.DatabaseContract.HeroEntry.COLUMN_STAMINA_MAX;
import static com.kgames.james.nerdybook.Hero.DatabaseContract.HeroEntry.COLUMN_STUFF3;
import static com.kgames.james.nerdybook.Hero.DatabaseContract.HeroEntry.COLUMN_TOTAL_CHAPTERS;
import static com.kgames.james.nerdybook.Hero.DatabaseContract.HeroEntry.TABLE_NAME;
import static com.kgames.james.nerdybook.Hero.DatabaseContract.SQL_DELETE_HERO;

/**
 * Database Helper for Heroes.
 */

public class HeroDBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Heroes.db";

    public int mCurrentAbility;
    public int mCurrentStamina;
    public int mCurrentLuck;

    public int mMaxAbility;
    public int mMaxStamina;
    public int mMaxLuck;

    public int mCurrentGoldCoins;
    public int mGoldCoins;

    public static final String SQL_CREATE_HERO =
            "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" +
                    DatabaseContract.HeroEntry.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    DatabaseContract.HeroEntry.COLUMN_ADVENTURE + " TEXT," +
                    DatabaseContract.HeroEntry.COLUMN_DIFFICULTY + " TEXT," +
                    DatabaseContract.HeroEntry.COLUMN_PLAYER_NAME + " TEXT," +
                    DatabaseContract.HeroEntry.COLUMN_ABILITY_MAX + " INTEGER," +
                    DatabaseContract.HeroEntry.COLUMN_ABILITY_CURRENT + " INTEGER," +
                    DatabaseContract.HeroEntry.COLUMN_STAMINA_MAX + " INTEGER," +
                    DatabaseContract.HeroEntry.COLUMN_STAMINA_CURRENT + " INTEGER," +
                    DatabaseContract.HeroEntry.COLUMN_LUCK_MAX + " INTEGER," +
                    DatabaseContract.HeroEntry.COLUMN_LUCK_CURRENT + " INTEGER," +
                    DatabaseContract.HeroEntry.COLUMN_ABILITY_POTIONS + " INTEGER," +
                    DatabaseContract.HeroEntry.COLUMN_STAMINA_POTIONS + " INTEGER," +
                    DatabaseContract.HeroEntry.COLUMN_LUCK_POTIONS + " INTEGER," +
                    DatabaseContract.HeroEntry.COLUMN_TORCHS + " INTEGER," +
                    DatabaseContract.HeroEntry.COLUMN_GC + " INTEGER," +
                    DatabaseContract.HeroEntry.COLUMN_MEALS + " INTEGER," +
                    DatabaseContract.HeroEntry.COLUMN_STUFF1 + " TEXT," +
                    DatabaseContract.HeroEntry.COLUMN_STUFF2 + " TEXT," +
                    DatabaseContract.HeroEntry.COLUMN_STUFF3 + " TEXT," +
                    DatabaseContract.HeroEntry.COLUMN_CURRENT_CHAPTER + " INTEGER," +
                    DatabaseContract.HeroEntry.COLUMN_TOTAL_CHAPTERS + " INTEGER);";

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


    // Update current and total chapters
    public void updateChapters(String heroID, int currentChapter, int totalChapters) {
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_CURRENT_CHAPTER, currentChapter);
        contentValues.put(COLUMN_TOTAL_CHAPTERS, totalChapters);
        db.update(TABLE_NAME, contentValues, COLUMN_ID + " = ?", new String[]{heroID});
        db.close();
    }


    //Get current Hero
    public HeroModel getCurrentHero(String heroID) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME +
                " WHERE " + COLUMN_ID + " = " + heroID, null);

        HeroModel currentHero = new HeroModel();

        if (cursor.moveToFirst()) {
            do {
                currentHero = new HeroModel(
                        cursor.getString(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getInt(4),
                        cursor.getInt(5),
                        cursor.getInt(6),
                        cursor.getInt(7),
                        cursor.getInt(8),
                        cursor.getInt(9),
                        cursor.getInt(10),
                        cursor.getInt(11),
                        cursor.getInt(12),
                        cursor.getInt(13),
                        cursor.getInt(14),
                        cursor.getInt(15),
                        cursor.getString(16),
                        cursor.getString(17),
                        cursor.getString(18),
                        cursor.getInt(19),
                        cursor.getInt(20)
                );

            } while (cursor.moveToNext());

        }

        return currentHero;

    }


    //Current Ability, Ability gain and Ability loss
    public int currentAbility(String heroID) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT " + COLUMN_ABILITY_CURRENT +
                " FROM " + TABLE_NAME + " WHERE " + COLUMN_ID + " = " + heroID, null);

        if (cursor.moveToFirst()) {
            mCurrentAbility = cursor.getInt(cursor.getColumnIndex(COLUMN_ABILITY_CURRENT));
            cursor.close();
        }

        db.close();
        return mCurrentAbility;
    }

    public void currentAbilityLoss(String heroID, int currentAbilityLoss) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT " + COLUMN_ABILITY_CURRENT +
                " FROM " + TABLE_NAME + " WHERE " + COLUMN_ID + " = " + heroID, null);

        if (cursor.moveToFirst()) {
            mCurrentAbility = cursor.getInt(cursor.getColumnIndex(COLUMN_ABILITY_CURRENT));
            cursor.close();
        }

        if (mCurrentAbility - currentAbilityLoss <= 0) {

            ContentValues contentValues = new ContentValues();
            contentValues.put(COLUMN_ABILITY_CURRENT, 0);
            db.update(TABLE_NAME, contentValues, COLUMN_ID + " = ?", new String[]{heroID});
            db.close();

        } else if (mCurrentAbility - currentAbilityLoss > 0) {

            ContentValues contentValues = new ContentValues();
            contentValues.put(COLUMN_ABILITY_CURRENT, mCurrentAbility - currentAbilityLoss);
            db.update(TABLE_NAME, contentValues, COLUMN_ID + " = ?", new String[]{heroID});
            db.close();
        }
    }

    public void currentAbilityGain(String heroID, int currentAbilityGain) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT " + COLUMN_ABILITY_CURRENT +
                " FROM " + TABLE_NAME + " WHERE " + COLUMN_ID + " = " + heroID, null);

        if (cursor.moveToFirst()) {
            mCurrentAbility = cursor.getInt(cursor.getColumnIndex(COLUMN_ABILITY_CURRENT));
            cursor.close();
        }
        Cursor cursorMax = db.rawQuery("SELECT " + COLUMN_ABILITY_MAX +
                " FROM " + TABLE_NAME + " WHERE " + COLUMN_ID + " = " + heroID, null);

        if (cursorMax.moveToFirst()) {
            mMaxAbility = cursorMax.getInt(cursor.getColumnIndex(COLUMN_ABILITY_MAX));
            cursor.close();
        }

        if (mCurrentAbility + currentAbilityGain > mMaxAbility) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(COLUMN_ABILITY_CURRENT, mMaxAbility);
            db.update(TABLE_NAME, contentValues, COLUMN_ID + " = ?", new String[]{heroID});
            db.close();
        } else {
            ContentValues contentValues = new ContentValues();
            contentValues.put(COLUMN_ABILITY_CURRENT, mCurrentAbility + currentAbilityGain);
            db.update(TABLE_NAME, contentValues, COLUMN_ID + " = ?", new String[]{heroID});
            db.close();
        }
    }

    // Stamina potion use
    public void useAbilityPotion(String heroID) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT " + COLUMN_ABILITY_MAX +
                " FROM " + TABLE_NAME + " WHERE " + COLUMN_ID + " = " + heroID, null);

        if (cursor.moveToFirst()) {
            mCurrentAbility = cursor.getInt(cursor.getColumnIndex(COLUMN_ABILITY_MAX));
            cursor.close();
        }

        ContentValues contentValuesCurrent = new ContentValues();
        contentValuesCurrent.put(COLUMN_ABILITY_CURRENT, mCurrentAbility);
        db.update(TABLE_NAME, contentValuesCurrent, COLUMN_ID + " = ?", new String[]{heroID});
        db.close();
    }


    // Current Stamina, Stamina gain and Stamina loss
    public int currentStamina(String heroID) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT " + COLUMN_STAMINA_CURRENT +
                " FROM " + TABLE_NAME + " WHERE " + COLUMN_ID + " = " + heroID, null);

        if (cursor.moveToFirst()) {
            mCurrentStamina = cursor.getInt(cursor.getColumnIndex(COLUMN_STAMINA_CURRENT));
            cursor.close();
        }

        db.close();
        return mCurrentStamina;
    }

    public void currentStaminaLoss(String heroID, int currentStaminaLoss) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT " + COLUMN_STAMINA_CURRENT +
                " FROM " + TABLE_NAME + " WHERE " + COLUMN_ID + " = " + heroID, null);

        if (cursor.moveToFirst()) {
            mCurrentStamina = cursor.getInt(cursor.getColumnIndex(COLUMN_STAMINA_CURRENT));
            cursor.close();
        }

        if (mCurrentStamina - currentStaminaLoss <= 0) {

            ContentValues contentValues = new ContentValues();
            contentValues.put(COLUMN_STAMINA_CURRENT, 0);
            db.update(TABLE_NAME, contentValues, COLUMN_ID + " = ?", new String[]{heroID});
            db.close();

        } else if (mCurrentStamina - currentStaminaLoss > 0) {

            ContentValues contentValues = new ContentValues();
            contentValues.put(COLUMN_STAMINA_CURRENT, mCurrentStamina - currentStaminaLoss);
            db.update(TABLE_NAME, contentValues, COLUMN_ID + " = ?", new String[]{heroID});
            db.close();
        }
    }

    public void currentStaminaGain(String heroID, int currentStaminaGain) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT " + COLUMN_STAMINA_CURRENT +
                " FROM " + TABLE_NAME + " WHERE " + COLUMN_ID + " = " + heroID, null);

        if (cursor.moveToFirst()) {
            mCurrentStamina = cursor.getInt(cursor.getColumnIndex(COLUMN_STAMINA_CURRENT));
            cursor.close();
        }
        Cursor cursorMax = db.rawQuery("SELECT " + COLUMN_STAMINA_MAX +
                " FROM " + TABLE_NAME + " WHERE " + COLUMN_ID + " = " + heroID, null);

        if (cursorMax.moveToFirst()) {
            mMaxStamina = cursorMax.getInt(cursor.getColumnIndex(COLUMN_STAMINA_MAX));
            cursor.close();
        }

        if (mCurrentStamina + currentStaminaGain > mMaxStamina) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(COLUMN_STAMINA_CURRENT, mMaxStamina);
            db.update(TABLE_NAME, contentValues, COLUMN_ID + " = ?", new String[]{heroID});
            db.close();
        } else {
            ContentValues contentValues = new ContentValues();
            contentValues.put(COLUMN_STAMINA_CURRENT, mCurrentStamina + currentStaminaGain);
            db.update(TABLE_NAME, contentValues, COLUMN_ID + " = ?", new String[]{heroID});
            db.close();
        }
    }

    // Stamina potion use
    public void useStaminaPotion(String heroID) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT " + COLUMN_STAMINA_MAX +
                " FROM " + TABLE_NAME + " WHERE " + COLUMN_ID + " = " + heroID, null);

        if (cursor.moveToFirst()) {
            mCurrentStamina = cursor.getInt(cursor.getColumnIndex(COLUMN_STAMINA_MAX));
            cursor.close();
        }

        ContentValues contentValuesCurrent = new ContentValues();
        contentValuesCurrent.put(COLUMN_STAMINA_CURRENT, mCurrentStamina);
        db.update(TABLE_NAME, contentValuesCurrent, COLUMN_ID + " = ?", new String[]{heroID});
        db.close();
    }


    // Current Luck, Luck gain and Luck loss
    public int currentLuck(String heroID) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT " + COLUMN_LUCK_CURRENT +
                " FROM " + TABLE_NAME + " WHERE " + COLUMN_ID + " = " + heroID, null);

        if (cursor.moveToFirst()) {
            mCurrentLuck = cursor.getInt(cursor.getColumnIndex(COLUMN_LUCK_CURRENT));
            cursor.close();
        }

        db.close();
        return mCurrentLuck;
    }

    public void currentLuckLoss(String heroID, int currentLuckLoss) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT " + COLUMN_LUCK_CURRENT +
                " FROM " + TABLE_NAME + " WHERE " + COLUMN_ID + " = " + heroID, null);

        if (cursor.moveToFirst()) {
            mCurrentLuck = cursor.getInt(cursor.getColumnIndex(COLUMN_LUCK_CURRENT));
            cursor.close();
        }

        if (mCurrentLuck - currentLuckLoss <= 0) {

            ContentValues contentValues = new ContentValues();
            contentValues.put(COLUMN_LUCK_CURRENT, 0);
            db.update(TABLE_NAME, contentValues, COLUMN_ID + " = ?", new String[]{heroID});
            db.close();

        } else if (mCurrentLuck - currentLuckLoss > 0) {

            ContentValues contentValues = new ContentValues();
            contentValues.put(COLUMN_LUCK_CURRENT, mCurrentLuck - currentLuckLoss);
            db.update(TABLE_NAME, contentValues, COLUMN_ID + " = ?", new String[]{heroID});
            db.close();
        }

    }

    public void currentLuckGain(String heroID, int currentLuckGain) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT " + COLUMN_LUCK_CURRENT +
                " FROM " + TABLE_NAME + " WHERE " + COLUMN_ID + " = " + heroID, null);

        if (cursor.moveToFirst()) {
            mCurrentLuck = cursor.getInt(cursor.getColumnIndex(COLUMN_LUCK_CURRENT));
            cursor.close();
        }

        Cursor cursorMax = db.rawQuery("SELECT " + COLUMN_LUCK_MAX +
                " FROM " + TABLE_NAME + " WHERE " + COLUMN_ID + " = " + heroID, null);

        if (cursorMax.moveToFirst()) {
            mMaxLuck = cursorMax.getInt(cursor.getColumnIndex(COLUMN_LUCK_MAX));
            cursorMax.close();
        }

        if (mCurrentLuck + currentLuckGain > mMaxLuck) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(COLUMN_LUCK_CURRENT, mMaxLuck);
            db.update(TABLE_NAME, contentValues, COLUMN_ID + " = ?", new String[]{heroID});
            db.close();
        } else {
            ContentValues contentValues = new ContentValues();
            contentValues.put(COLUMN_LUCK_CURRENT, mCurrentLuck + currentLuckGain);
            db.update(TABLE_NAME, contentValues, COLUMN_ID + " = ?", new String[]{heroID});
            db.close();
        }
    }

    // Luck potion use
    public void useLuckPotion(String heroID) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT " + COLUMN_LUCK_CURRENT +
                " FROM " + TABLE_NAME + " WHERE " + COLUMN_ID + " = " + heroID, null);

        Cursor cursorMax = db.rawQuery("SELECT " + COLUMN_LUCK_MAX +
                " FROM " + TABLE_NAME + " WHERE " + COLUMN_ID + " = " + heroID, null);

        if (cursorMax.moveToFirst()) {
            mMaxLuck = cursorMax.getInt(cursor.getColumnIndex(COLUMN_LUCK_MAX));
            cursorMax.close();
            cursor.close();
        }

        ContentValues contentValuesCurrent = new ContentValues();
        contentValuesCurrent.put(COLUMN_LUCK_CURRENT, mMaxLuck + 1);
        db.update(TABLE_NAME, contentValuesCurrent, COLUMN_ID + " = ?", new String[]{heroID});
        db.close();

        ContentValues contentValuesMax = new ContentValues();
        contentValuesMax.put(COLUMN_LUCK_MAX, mMaxLuck + 1);
        db.update(TABLE_NAME, contentValuesMax, COLUMN_ID + " = ?", new String[]{heroID});
        db.close();
    }

    // Are you lucky ?
    public boolean isHeroLucky (String heroID) {

        boolean gotLuck = false;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT " + COLUMN_LUCK_CURRENT +
                " FROM " + TABLE_NAME + " WHERE " + COLUMN_ID + " = " + heroID, null);

        if (cursor.moveToFirst()) {
            mCurrentLuck = cursor.getInt(cursor.getColumnIndex(COLUMN_LUCK_CURRENT));
            cursor.close();
        }

        int firstDice = (int) ((6 * Math.random()) + 1);
        int secondDice = (int) ((6 * Math.random()) + 1);

        int fate = firstDice + secondDice;

        if (fate <= mCurrentLuck) {

            currentLuckLoss(heroID, 1);

            gotLuck = true;

        } else {

            currentLuckLoss(heroID, 1);

        }

        return gotLuck;

    }


    // Current gold coins, gold coins gain and loss
    public int currentGoldCoins(String heroID) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT " + COLUMN_GC +
                " FROM " + TABLE_NAME + " WHERE " + COLUMN_ID + " = " + heroID, null);

        if (cursor.moveToFirst()) {
            mCurrentGoldCoins = cursor.getInt(cursor.getColumnIndex(COLUMN_GC));
            cursor.close();
        }

        db.close();
        return mCurrentGoldCoins;
    }

    public void goldCoinsLoss(String heroID, int goldCoinsLoss) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT " + COLUMN_GC +
                " FROM " + TABLE_NAME + " WHERE " + COLUMN_ID + " = " + heroID, null);

        if (cursor.moveToFirst()) {
            mGoldCoins = cursor.getInt(cursor.getColumnIndex(COLUMN_GC));
            cursor.close();
        }

        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_GC, mGoldCoins - goldCoinsLoss);
        db.update(TABLE_NAME, contentValues, COLUMN_ID + " = ?", new String[]{heroID});
        db.close();
    }

    public void goldCoinGain(String heroID, int goldCoinsGain) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT " + COLUMN_GC +
                " FROM " + TABLE_NAME + " WHERE " + COLUMN_ID + " = " + heroID, null);

        if (cursor.moveToFirst()) {
            mGoldCoins = cursor.getInt(cursor.getColumnIndex(COLUMN_GC));
            cursor.close();
        }

        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_GC, mGoldCoins + goldCoinsGain);
        db.update(TABLE_NAME, contentValues, COLUMN_ID + " = ?", new String[]{heroID});
        db.close();
    }


    // Stuff3 (Talisman) gain
    public String stuff3TalismanGain(String heroID, String talisman) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_STUFF3, talisman);
        db.update(TABLE_NAME, contentValues, COLUMN_ID + " = ?", new String[]{heroID});
        db.close();

        return String.valueOf(R.string.talisman_stuff3);
    }


    boolean deleteHero(int heroID) {
        SQLiteDatabase db = getWritableDatabase();
        return db.delete(TABLE_NAME, COLUMN_ID + "=?", new String[]{String.valueOf(heroID)}) == 1;
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