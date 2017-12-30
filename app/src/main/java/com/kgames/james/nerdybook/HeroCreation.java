package com.kgames.james.nerdybook;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.kgames.james.nerdybook.Hero.DatabaseContract;
import com.kgames.james.nerdybook.Hero.HeroDBHelper;

import static com.kgames.james.nerdybook.Hero.DatabaseContract.HeroEntry.COLUMN_ABILITY_CURRENT;
import static com.kgames.james.nerdybook.Hero.DatabaseContract.HeroEntry.COLUMN_ABILITY_MAX;
import static com.kgames.james.nerdybook.Hero.DatabaseContract.HeroEntry.COLUMN_ADVENTURE;
import static com.kgames.james.nerdybook.Hero.DatabaseContract.HeroEntry.COLUMN_CURRENT_CHAPTER;
import static com.kgames.james.nerdybook.Hero.DatabaseContract.HeroEntry.COLUMN_DIFFICULTY;
import static com.kgames.james.nerdybook.Hero.DatabaseContract.HeroEntry.COLUMN_LUCK_CURRENT;
import static com.kgames.james.nerdybook.Hero.DatabaseContract.HeroEntry.COLUMN_LUCK_MAX;
import static com.kgames.james.nerdybook.Hero.DatabaseContract.HeroEntry.COLUMN_PLAYER_GENDER;
import static com.kgames.james.nerdybook.Hero.DatabaseContract.HeroEntry.COLUMN_PLAYER_NAME;
import static com.kgames.james.nerdybook.Hero.DatabaseContract.HeroEntry.COLUMN_STAMINA_CURRENT;
import static com.kgames.james.nerdybook.Hero.DatabaseContract.HeroEntry.COLUMN_STAMINA_MAX;
import static com.kgames.james.nerdybook.Hero.DatabaseContract.HeroEntry.COLUMN_TOTAL_CHAPTERS;

public class HeroCreation extends AppCompatActivity {

    HeroDBHelper mDbHelper;

    String mDifficulty;
    String mAdventure;
    String mHeroName;
    String mHeroGender;

    String mAbilityMax;
    String mAbilityCurrent;
    String mStaminaMax;
    String mStaminaCurrent;
    String mLuckMax;
    String mLuckCurrent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hero_creation);

        mAdventure = getIntent().getExtras().getString("AdventureTalisman");

        final EditText heroNameEntry = findViewById(R.id.hero_name_value);

        final TextView heroAbility = findViewById(R.id.hero_ability_value);
        final TextView heroStamina = findViewById(R.id.hero_stamina_value);
        final TextView heroLuck = findViewById(R.id.hero_luck_value);

        heroStamina.setText(String.valueOf(R.string.hero_gender_hint));
        heroLuck.setText(String.valueOf(R.string.hero_gender_hint));
        heroAbility.setText(String.valueOf(R.string.hero_gender_hint));

        final Button difficultyValidate = findViewById(R.id.validate_difficulty);
        final Button heroNameValidate = findViewById(R.id.hero_name_creation);
        final Button heroGenderValidate = findViewById(R.id.validate_gender);
        final Button abilityDices = findViewById(R.id.hero_ability_dices);
        final Button staminaDices = findViewById(R.id.hero_stamina_dices);
        final Button luckDices = findViewById(R.id.hero_luck_dices);

        final Button startAdventure = findViewById(R.id.start_adventure);
        startAdventure.setEnabled(false);

        if (heroGenderValidate.isEnabled()) {
            heroAbility.setText(R.string.hero_gender_hint);
            heroStamina.setText(R.string.hero_gender_hint);
            heroLuck.setText(R.string.hero_gender_hint);
        }

        heroNameValidate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!TextUtils.isEmpty(heroNameEntry.getText())){
                    mHeroName = heroNameEntry.getText().toString();

                    heroNameEntry.setEnabled(false);
                    heroNameValidate.setEnabled(false);

                    if (!difficultyValidate.isEnabled() &&
                            !heroNameValidate.isEnabled() &&
                            !heroGenderValidate.isEnabled() &&
                            !abilityDices.isEnabled() &&
                            !staminaDices.isEnabled() &&
                            !luckDices.isEnabled() ) {
                        startAdventure.setEnabled(true);
                    } else {
                        startAdventure.setEnabled(false);
                    }

                } else {
                    Toast.makeText(HeroCreation.this, R.string.hero_name_empty, Toast.LENGTH_SHORT).show();
                }
            }
        });

        abilityDices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int heroAbilityValue = (int) ((6 * Math.random() + 1)) + 6;
                heroAbility.setText(String.valueOf(heroAbilityValue));
                mAbilityMax = String.valueOf(heroAbilityValue);
                mAbilityCurrent = String.valueOf(heroAbilityValue);

                abilityDices.setEnabled(false);

                if (!difficultyValidate.isEnabled() &&
                        !heroNameValidate.isEnabled() &&
                        !heroGenderValidate.isEnabled() &&
                        !abilityDices.isEnabled() &&
                        !staminaDices.isEnabled() &&
                        !luckDices.isEnabled() ) {
                    startAdventure.setEnabled(true);
                } else {
                    startAdventure.setEnabled(false);
                }

            }
        });

        staminaDices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int heroStaminaValue = (int) ((6 * Math.random()) + 1) + 12;
                heroStamina.setText(String.valueOf(heroStaminaValue));
                mStaminaMax = String.valueOf(heroStaminaValue);
                mStaminaCurrent = String.valueOf(heroStaminaValue);

                staminaDices.setEnabled(false);

                if (!difficultyValidate.isEnabled() &&
                        !heroNameValidate.isEnabled() &&
                        !heroGenderValidate.isEnabled() &&
                        !abilityDices.isEnabled() &&
                        !staminaDices.isEnabled() &&
                        !luckDices.isEnabled() ) {
                    startAdventure.setEnabled(true);
                } else {
                    startAdventure.setEnabled(false);
                }

            }
        });

        luckDices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int heroLuckValue = (int) ((6 * Math.random()) + 1) + 6;
                heroLuck.setText(String.valueOf(heroLuckValue));
                mLuckMax = String.valueOf(heroLuckValue);
                mLuckCurrent = String.valueOf(heroLuckValue);

                luckDices.setEnabled(false);

                if (!difficultyValidate.isEnabled() &&
                        !heroNameValidate.isEnabled() &&
                        !heroGenderValidate.isEnabled() &&
                        !abilityDices.isEnabled() &&
                        !staminaDices.isEnabled() &&
                        !luckDices.isEnabled() ) {
                    startAdventure.setEnabled(true);
                } else {
                    startAdventure.setEnabled(false);
                }

            }
        });

        startAdventure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mDbHelper = new HeroDBHelper(HeroCreation.this);
                SQLiteDatabase database = mDbHelper.getWritableDatabase();

                ContentValues contentValues = new ContentValues();
                contentValues.put(COLUMN_ADVENTURE, mAdventure);
                contentValues.put(COLUMN_DIFFICULTY, mDifficulty);
                contentValues.put(COLUMN_PLAYER_NAME, mHeroName);
                contentValues.put(COLUMN_PLAYER_GENDER, mHeroGender);
                contentValues.put(COLUMN_ABILITY_MAX, mAbilityMax);
                contentValues.put(COLUMN_ABILITY_CURRENT, mAbilityCurrent);
                contentValues.put(COLUMN_STAMINA_MAX, mStaminaMax);
                contentValues.put(COLUMN_STAMINA_CURRENT, mStaminaCurrent);
                contentValues.put(COLUMN_LUCK_MAX, mLuckMax);
                contentValues.put(COLUMN_LUCK_CURRENT, mLuckCurrent);
                contentValues.put(COLUMN_CURRENT_CHAPTER, "0");
                contentValues.put(COLUMN_TOTAL_CHAPTERS, "0");
                long heroID = database.insert(DatabaseContract.HeroEntry.TABLE_NAME, null, contentValues);

                Intent intent = new Intent(HeroCreation.this, AdventureTalisman.class);
                intent.putExtra("HeroID", String.valueOf(heroID));
                intent.putExtra("CurrentChapter", "0");
                intent.putExtra("TotalChapters", "0");
                startActivity(intent);
            }
        });

    }


    public void onRadioButtonGender(View view) {

        final RadioButton maleHero = findViewById(R.id.hero_selection_male);
        final RadioButton femaleHero = findViewById(R.id.hero_selection_female);

        final TextView heroAbility = findViewById(R.id.hero_ability_value);
        final TextView heroStamina = findViewById(R.id.hero_stamina_value);
        final TextView heroLuck = findViewById(R.id.hero_luck_value);

        final Button difficultyValidate = findViewById(R.id.validate_difficulty);
        final Button heroNameValidate = findViewById(R.id.hero_name_creation);
        final Button heroGenderValidate = findViewById(R.id.validate_gender);
        final Button abilityDices = findViewById(R.id.hero_ability_dices);
        final Button staminaDices = findViewById(R.id.hero_stamina_dices);
        final Button luckDices = findViewById(R.id.hero_luck_dices);

        final Button startAdventure = findViewById(R.id.start_adventure);

        heroGenderValidate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (maleHero.isChecked()) {
                    femaleHero.setChecked(false);
                    mHeroGender = getString(R.string.hero_male_sql);

                    if (abilityDices.isEnabled()) {
                        heroAbility.setText(R.string.hero_ability_hint_male);
                    }

                    if (staminaDices.isEnabled()) {
                        heroStamina.setText(R.string.hero_stamina_hint_male);
                    }

                    if (luckDices.isEnabled()) {
                        heroLuck.setText(R.string.hero_luck_hint_male);
                    }

                } else {

                    maleHero.setChecked(false);
                    mHeroGender = getString(R.string.hero_female_sql);

                    if(abilityDices.isEnabled()) {
                        heroAbility.setText(R.string.hero_ability_hint_female);
                    }

                    if(staminaDices.isEnabled()) {
                        heroStamina.setText(R.string.hero_stamina_hint_female);
                    }

                    if (luckDices.isEnabled()) {
                        heroLuck.setText(R.string.hero_luck_hint_female);
                    }

                }

                maleHero.setEnabled(false);
                femaleHero.setEnabled(false);
                heroGenderValidate.setEnabled(false);

                if (!difficultyValidate.isEnabled() &&
                        !heroNameValidate.isEnabled() &&
                        !heroGenderValidate.isEnabled() &&
                        !abilityDices.isEnabled() &&
                        !staminaDices.isEnabled() &&
                        !luckDices.isEnabled() ) {
                    startAdventure.setEnabled(true);
                } else {
                    startAdventure.setEnabled(false);
                }

            }
        });
    }


    public void onRadioButtonDifficulty(View view) {

        final RadioButton easyDifficulty = findViewById(R.id.easy_adventure);
        final RadioButton normalDifficulty = findViewById(R.id.normal_adventure);

        final Button difficultyValidate = findViewById(R.id.validate_difficulty);
        final Button heroNameValidate = findViewById(R.id.hero_name_creation);
        final Button heroGenderValidate = findViewById(R.id.validate_gender);
        final Button abilityDices = findViewById(R.id.hero_ability_dices);
        final Button staminaDices = findViewById(R.id.hero_stamina_dices);
        final Button luckDices = findViewById(R.id.hero_luck_dices);

        final Button startAdventure = findViewById(R.id.start_adventure);

        difficultyValidate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (easyDifficulty.isChecked()) {
                    normalDifficulty.setChecked(false);
                    mDifficulty = getString(R.string.easy_difficulty_sql);

                } else {
                    easyDifficulty.setChecked(false);
                    mDifficulty = getString(R.string.normal_difficulty_sql);

                }

                easyDifficulty.setEnabled(false);
                normalDifficulty.setEnabled(false);
                difficultyValidate.setEnabled(false);

                if (!difficultyValidate.isEnabled() &&
                        !heroNameValidate.isEnabled() &&
                        !heroGenderValidate.isEnabled() &&
                        !abilityDices.isEnabled() &&
                        !staminaDices.isEnabled() &&
                        !luckDices.isEnabled() ) {
                    startAdventure.setEnabled(true);
                } else {
                    startAdventure.setEnabled(false);
                }

            }
        });
    }

}
