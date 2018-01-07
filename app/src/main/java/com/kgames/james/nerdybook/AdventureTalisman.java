package com.kgames.james.nerdybook;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.kgames.james.nerdybook.Hero.HeroDBHelper;

public class AdventureTalisman extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    Toolbar mToolbar;
    HeroDBHelper mHeroDBHelper;

    String mHeroID;
    int mCurrentChapter;
    int mTotalChapters = 0;
    int mAbilityLoss;
    int mAbilityCurrent;
    int mStaminaLoss;
    int mStaminaCurrent;
    int mLuckGain;
    int mLuckLoss;
    int mLuckCurrent;
    int mGoldCoinsLoss;
    int mGoldCoinsCurrent;
    int mGoldCoinsGain;
    int mMealsCurrent;
    int mMealsUse;
    int mMealsLoss;
    String mStuff3Gain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adventure_talisman);

        // Toolbar
        mToolbar = findViewById(R.id.toolbar);
        mToolbar.setTitle("");
        mToolbar.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        setSupportActionBar(mToolbar);


        // Drawer Menu
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        final NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);
        Menu nav_Menu = navigationView.getMenu();
        nav_Menu.findItem(R.id.nav_current_adventure).setVisible(false);
        View headerview = navigationView.getHeaderView(0);
        headerview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), AdventureTalisman.class));
            }
        });


        mHeroDBHelper = new HeroDBHelper(AdventureTalisman.this);

        mHeroID = getIntent().getExtras().getString("HeroID");
        mCurrentChapter = getIntent().getExtras().getInt("CurrentChapter");
        mTotalChapters = getIntent().getExtras().getInt("TotalChapters");

        //Typeface mainFont = Typeface.createFromAsset(getAssets(), "fonts/Dosmilcatorce.ttf");

        final TextView adventureContent = findViewById(R.id.adventure_content);
        final TextView statsChange1 = findViewById(R.id.stats_change1);
        final TextView statsChange2 = findViewById(R.id.stats_change2);
        final TextView statsChange3 = findViewById(R.id.stats_change3);

        //adventureContent.setTypeface(mainFont);

        final Button choice1 = findViewById(R.id.choice_1);
        final Button choice2 = findViewById(R.id.choice_2);
        final Button choice3 = findViewById(R.id.choice_3);
        final Button choice4 = findViewById(R.id.choice_4);
        final Button choice5 = findViewById(R.id.choice_5);

        Thread runningAdventure = new Thread() {

            @Override
            public void run() {
                try {
                    while (!isInterrupted()) {
                        Thread.sleep(500);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                                if (mCurrentChapter == 0) {

                                    mToolbar.setTitle(String.format(getString(R.string.current_chapter),
                                            String.valueOf(mTotalChapters)));

                                    choice1.setVisibility(View.VISIBLE);
                                    choice2.setVisibility(View.GONE);
                                    choice3.setVisibility(View.GONE);
                                    choice4.setVisibility(View.GONE);
                                    choice5.setVisibility(View.GONE);

                                    adventureContent.setText(getString(R.string.talisman_chapter0));
                                    choice1.setText(R.string.talisman_chapter0_choice0);

                                    choice1.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {

                                            mCurrentChapter = 1;
                                            mTotalChapters = mTotalChapters + 1;
                                            mHeroDBHelper.updateChapters(mHeroID, mCurrentChapter, mTotalChapters);

                                        }
                                    });

                                }

                                if (mCurrentChapter == 1) {

                                    mToolbar.setTitle(String.format(getString(R.string.current_chapter),
                                            String.valueOf(mTotalChapters)));

                                    choice1.setVisibility(View.VISIBLE);
                                    choice2.setVisibility(View.VISIBLE);
                                    choice3.setVisibility(View.GONE);
                                    choice4.setVisibility(View.GONE);
                                    choice5.setVisibility(View.GONE);

                                    adventureContent.setText(getString(R.string.talisman_chapter1));
                                    choice1.setText(R.string.talisman_chapter1_choice1);
                                    choice2.setText(R.string.talisman_chapter1_choice2);

                                    choice1.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {

                                            mCurrentChapter = 17;
                                            mTotalChapters = mTotalChapters + 1;
                                            mHeroDBHelper.updateChapters(mHeroID, mCurrentChapter, mTotalChapters);

                                        }
                                    });

                                    choice2.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {

                                            mCurrentChapter = 30;
                                            mTotalChapters = mTotalChapters + 1;
                                            mHeroDBHelper.updateChapters(mHeroID, mCurrentChapter, mTotalChapters);

                                        }
                                    });

                                }

                                if (mCurrentChapter == 5) {

                                    mToolbar.setTitle(String.format(getString(R.string.current_chapter),
                                            String.valueOf(mTotalChapters)));

                                    statsChange1.setVisibility(View.VISIBLE);
                                    statsChange2.setVisibility(View.VISIBLE);

                                    mAbilityLoss = 1;
                                    mStaminaLoss = 2;
                                    mAbilityCurrent = mHeroDBHelper.currentAbility(mHeroID) + mAbilityLoss;
                                    mStaminaCurrent = mHeroDBHelper.currentStamina(mHeroID) + mStaminaLoss;

                                    if (mStaminaCurrent - mStaminaLoss <= 0) {

                                        statsChange1.setText(getString(R.string.hero_stamina_null));

                                        choice1.setVisibility(View.VISIBLE);
                                        choice2.setVisibility(View.GONE);
                                        choice3.setVisibility(View.GONE);
                                        choice4.setVisibility(View.GONE);
                                        choice5.setVisibility(View.GONE);

                                        choice1.setText(getString(R.string.hero_death));

                                        choice1.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {

                                            }
                                        });

                                    } else {

                                        choice1.setVisibility(View.VISIBLE);
                                        choice2.setVisibility(View.GONE);
                                        choice3.setVisibility(View.GONE);
                                        choice4.setVisibility(View.GONE);
                                        choice5.setVisibility(View.GONE);

                                        adventureContent.setText(getString(R.string.talisman_chapter5));
                                        choice1.setText(R.string.talisman_chapter5_choice1);

                                        statsChange1.setText(String.format(getString(R.string.hero_ability_loss),
                                                String.valueOf(mAbilityLoss), String.valueOf(mAbilityCurrent - mAbilityLoss)));

                                        statsChange2.setText(String.format(getString(R.string.hero_stamina_loss),
                                                String.valueOf(mStaminaLoss), String.valueOf(mStaminaCurrent - mStaminaLoss)));

                                        choice1.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {

                                                mLuckLoss = 1;
                                                mHeroDBHelper.currentLuckLoss(mHeroID, mLuckLoss);

                                                mCurrentChapter = 75;
                                                mTotalChapters = mTotalChapters + 1;
                                                mHeroDBHelper.updateChapters(mHeroID, mCurrentChapter, mTotalChapters);

                                            }
                                        });
                                    }

                                }

                                if (mCurrentChapter == 13) {

                                    mToolbar.setTitle(String.format(getString(R.string.current_chapter),
                                            String.valueOf(mTotalChapters)));

                                    choice1.setVisibility(View.VISIBLE);
                                    choice2.setVisibility(View.VISIBLE);
                                    choice3.setVisibility(View.VISIBLE);
                                    choice4.setVisibility(View.VISIBLE);
                                    choice5.setVisibility(View.GONE);

                                    statsChange1.setVisibility(View.GONE);

                                    adventureContent.setText(getString(R.string.talisman_chapter13));
                                    choice1.setText(R.string.talisman_chapter13_choice1);
                                    choice2.setText(R.string.talisman_chapter13_choice2);
                                    choice3.setText(R.string.talisman_chapter13_choice3);
                                    choice4.setText(R.string.talisman_chapter13_choice4);

                                    choice1.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {

                                            mCurrentChapter = 247;
                                            mTotalChapters = mTotalChapters + 1;
                                            mHeroDBHelper.updateChapters(mHeroID, mCurrentChapter, mTotalChapters);

                                        }
                                    });

                                    choice2.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {

                                            mCurrentChapter = 60;
                                            mTotalChapters = mTotalChapters + 1;
                                            mHeroDBHelper.updateChapters(mHeroID, mCurrentChapter, mTotalChapters);

                                        }
                                    });

                                    choice3.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {

                                            mLuckLoss = 1;
                                            mHeroDBHelper.currentLuckLoss(mHeroID, mLuckLoss);

                                            mCurrentChapter = 75;
                                            mTotalChapters = mTotalChapters + 1;
                                            mHeroDBHelper.updateChapters(mHeroID, mCurrentChapter, mTotalChapters);

                                        }
                                    });

                                    choice4.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {

                                            mAbilityLoss = 1;
                                            mHeroDBHelper.currentAbilityLoss(mHeroID, mAbilityLoss);

                                            mStaminaLoss = 2;
                                            mHeroDBHelper.currentStaminaLoss(mHeroID, mStaminaLoss);

                                            mCurrentChapter = 5;
                                            mTotalChapters = mTotalChapters + 1;
                                            mHeroDBHelper.updateChapters(mHeroID, mCurrentChapter, mTotalChapters);

                                        }
                                    });

                                }

                                if (mCurrentChapter == 17) {

                                    mToolbar.setTitle(String.format(getString(R.string.current_chapter),
                                            String.valueOf(mTotalChapters)));

                                    choice1.setVisibility(View.VISIBLE);
                                    choice2.setVisibility(View.VISIBLE);
                                    choice3.setVisibility(View.GONE);
                                    choice4.setVisibility(View.GONE);
                                    choice5.setVisibility(View.GONE);

                                    adventureContent.setText(getString(R.string.talisman_chapter17));
                                    choice1.setText(R.string.talisman_chapter17_choice1);
                                    choice2.setText(R.string.talisman_chapter17_choice2);

                                    choice1.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {

                                            mStaminaLoss = 2;
                                            mStaminaCurrent = mHeroDBHelper.currentStamina(mHeroID);
                                            mHeroDBHelper.currentStaminaLoss(mHeroID, mStaminaLoss);

                                            mCurrentChapter = 41;
                                            mTotalChapters = mTotalChapters + 1;
                                            mHeroDBHelper.updateChapters(mHeroID, mCurrentChapter, mTotalChapters);

                                        }
                                    });

                                    choice2.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {

                                            mCurrentChapter = 21;
                                            mTotalChapters = mTotalChapters + 1;
                                            mHeroDBHelper.updateChapters(mHeroID, mCurrentChapter, mTotalChapters);

                                        }
                                    });

                                }

                                if (mCurrentChapter == 21) {

                                    mToolbar.setTitle(String.format(getString(R.string.current_chapter),
                                            String.valueOf(mTotalChapters)));

                                    choice1.setVisibility(View.VISIBLE);
                                    choice2.setVisibility(View.GONE);
                                    choice3.setVisibility(View.GONE);
                                    choice4.setVisibility(View.GONE);
                                    choice5.setVisibility(View.GONE);

                                    adventureContent.setText(getString(R.string.talisman_chapter21));
                                    choice1.setText(R.string.talisman_chapter21_choice1);

                                    choice1.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {

                                            mCurrentChapter = 13;
                                            mTotalChapters = mTotalChapters + 1;
                                            mHeroDBHelper.updateChapters(mHeroID, mCurrentChapter, mTotalChapters);

                                        }
                                    });

                                }

                                if (mCurrentChapter == 30) {

                                    mToolbar.setTitle(String.format(getString(R.string.current_chapter),
                                            String.valueOf(mTotalChapters)));

                                    choice1.setVisibility(View.VISIBLE);
                                    choice2.setVisibility(View.GONE);
                                    choice3.setVisibility(View.GONE);
                                    choice4.setVisibility(View.GONE);
                                    choice5.setVisibility(View.GONE);

                                    adventureContent.setText(getString(R.string.talisman_chapter30));
                                    choice1.setText(R.string.talisman_chapter30_choice1);

                                    choice1.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {

                                            mCurrentChapter = 13;
                                            mTotalChapters = mTotalChapters + 1;
                                            mHeroDBHelper.updateChapters(mHeroID, mCurrentChapter, mTotalChapters);

                                        }
                                    });

                                }

                                if (mCurrentChapter == 36) {

                                    mToolbar.setTitle(String.format(getString(R.string.current_chapter),
                                            String.valueOf(mTotalChapters)));

                                    choice1.setVisibility(View.VISIBLE);
                                    choice2.setVisibility(View.GONE);
                                    choice3.setVisibility(View.GONE);
                                    choice4.setVisibility(View.GONE);
                                    choice5.setVisibility(View.GONE);

                                    adventureContent.setText(getString(R.string.talisman_chapter36));
                                    choice1.setText(R.string.talisman_chapter36_choice1);

                                    choice1.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {

                                            //TODO Fight !

                                        }
                                    });

                                }

                                if (mCurrentChapter == 41) {


                                    setSupportActionBar(mToolbar);
                                    mToolbar.setTitle(String.format(getString(R.string.current_chapter),
                                            String.valueOf(mTotalChapters)));

                                    statsChange1.setVisibility(View.VISIBLE);

                                    mStaminaLoss = 2;
                                    mStaminaCurrent = mHeroDBHelper.currentStamina(mHeroID) + mStaminaLoss;

                                    if (mStaminaCurrent - mStaminaLoss <= 0) {

                                        statsChange1.setText(getString(R.string.hero_stamina_null));

                                        choice1.setVisibility(View.VISIBLE);
                                        choice2.setVisibility(View.GONE);
                                        choice3.setVisibility(View.GONE);
                                        choice4.setVisibility(View.GONE);
                                        choice5.setVisibility(View.GONE);

                                        choice1.setText(getString(R.string.hero_death));

                                        choice1.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {

                                            }
                                        });

                                    } else {

                                        choice1.setVisibility(View.VISIBLE);
                                        choice2.setVisibility(View.GONE);
                                        choice3.setVisibility(View.GONE);
                                        choice4.setVisibility(View.GONE);

                                        adventureContent.setText(getString(R.string.talisman_chapter41));
                                        choice1.setText(R.string.talisman_chapter41_choice1);

                                        statsChange1.setText(String.format(getString(R.string.hero_stamina_loss),
                                                String.valueOf(mStaminaLoss), String.valueOf(mStaminaCurrent - mStaminaLoss)));

                                        choice1.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {

                                                mCurrentChapter = 13;
                                                mTotalChapters = mTotalChapters + 1;
                                                mHeroDBHelper.updateChapters(mHeroID, mCurrentChapter, mTotalChapters);

                                            }
                                        });
                                    }

                                }

                                if (mCurrentChapter == 52) {

                                    mToolbar.setTitle(String.format(getString(R.string.current_chapter),
                                            String.valueOf(mTotalChapters)));

                                    statsChange1.setVisibility(View.VISIBLE);
                                    statsChange2.setVisibility(View.VISIBLE);
                                    statsChange3.setVisibility(View.VISIBLE);

                                    mLuckGain = 1;
                                    mLuckCurrent = mHeroDBHelper.currentLuck(mHeroID) - mLuckGain;
                                    mMealsCurrent = mHeroDBHelper.currentMeals(mHeroID);

                                    choice1.setVisibility(View.VISIBLE);
                                    choice2.setVisibility(View.GONE);
                                    choice3.setVisibility(View.GONE);
                                    choice4.setVisibility(View.GONE);
                                    choice5.setVisibility(View.GONE);

                                    adventureContent.setText(getString(R.string.talisman_chapter52));
                                    choice1.setText(R.string.talisman_chapter52_choice1);

                                    statsChange1.setText(String.format(getString(R.string.hero_meals_loss),
                                            String.valueOf(mLuckGain), String.valueOf(mMealsCurrent - 1)));

                                    statsChange2.setText(String.format(getString(R.string.hero_luck_gain),
                                            String.valueOf(mLuckGain), String.valueOf(mLuckCurrent)));

                                    statsChange3.setText(String.format(getString(R.string.hero_meals_gain_golden_apple),
                                            String.valueOf(mMealsCurrent)));

                                    choice1.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {

                                            mCurrentChapter = 159;
                                            mTotalChapters = mTotalChapters + 1;
                                            mHeroDBHelper.updateChapters(mHeroID, mCurrentChapter, mTotalChapters);

                                        }
                                    });

                                }

                                if (mCurrentChapter == 60) {

                                    mToolbar.setTitle(String.format(getString(R.string.current_chapter),
                                            String.valueOf(mTotalChapters)));

                                    choice1.setVisibility(View.VISIBLE);
                                    choice2.setVisibility(View.VISIBLE);
                                    choice3.setVisibility(View.GONE);
                                    choice4.setVisibility(View.GONE);
                                    choice5.setVisibility(View.GONE);

                                    adventureContent.setText(getString(R.string.talisman_chapter60));
                                    choice1.setText(R.string.talisman_chapter60_choice1);
                                    choice2.setText(R.string.talisman_chapter60_choice2);

                                    choice1.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {

                                            mCurrentChapter = 70;
                                            mTotalChapters = mTotalChapters + 1;
                                            mHeroDBHelper.updateChapters(mHeroID, mCurrentChapter, mTotalChapters);

                                        }
                                    });


                                    choice2.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {

                                            mCurrentChapter = 75;
                                            mTotalChapters = mTotalChapters + 1;
                                            mHeroDBHelper.updateChapters(mHeroID, mCurrentChapter, mTotalChapters);

                                        }
                                    });

                                }

                                if (mCurrentChapter == 63) {

                                    mToolbar.setTitle(String.format(getString(R.string.current_chapter),
                                            String.valueOf(mTotalChapters)));

                                    choice1.setVisibility(View.VISIBLE);
                                    choice2.setVisibility(View.VISIBLE);
                                    choice3.setVisibility(View.VISIBLE);
                                    choice4.setVisibility(View.GONE);
                                    choice5.setVisibility(View.GONE);

                                    adventureContent.setText(getString(R.string.talisman_chapter63));
                                    choice1.setText(R.string.talisman_chapter63_choice1);
                                    choice2.setText(R.string.talisman_chapter63_choice2);
                                    choice2.setText(R.string.talisman_chapter63_choice3);

                                    choice1.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {

                                            mCurrentChapter = 214;
                                            mTotalChapters = mTotalChapters + 1;
                                            mHeroDBHelper.updateChapters(mHeroID, mCurrentChapter, mTotalChapters);

                                        }
                                    });

                                    choice2.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {

                                            mCurrentChapter = 282;
                                            mTotalChapters = mTotalChapters + 1;
                                            mHeroDBHelper.updateChapters(mHeroID, mCurrentChapter, mTotalChapters);

                                        }
                                    });

                                    choice3.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {

                                            mCurrentChapter = 227;
                                            mTotalChapters = mTotalChapters + 1;
                                            mHeroDBHelper.updateChapters(mHeroID, mCurrentChapter, mTotalChapters);

                                        }
                                    });

                                }

                                if (mCurrentChapter == 65) {

                                    mToolbar.setTitle(String.format(getString(R.string.current_chapter),
                                            String.valueOf(mTotalChapters)));

                                    choice1.setVisibility(View.VISIBLE);
                                    choice2.setVisibility(View.VISIBLE);
                                    choice3.setVisibility(View.VISIBLE);
                                    choice4.setVisibility(View.VISIBLE);
                                    choice5.setVisibility(View.VISIBLE);

                                    adventureContent.setText(getString(R.string.talisman_chapter65));
                                    choice1.setText(R.string.talisman_chapter65_choice1);
                                    choice2.setText(R.string.talisman_chapter65_choice2);
                                    choice3.setText(R.string.talisman_chapter65_choice3);
                                    choice4.setText(R.string.talisman_chapter65_choice4);
                                    choice5.setText(R.string.talisman_chapter65_choice5);

                                    choice1.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {

                                            mCurrentChapter = 101;
                                            mTotalChapters = mTotalChapters + 1;
                                            mHeroDBHelper.updateChapters(mHeroID, mCurrentChapter, mTotalChapters);

                                        }
                                    });

                                    choice2.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {

                                            mCurrentChapter = 73;
                                            mTotalChapters = mTotalChapters + 1;
                                            mHeroDBHelper.updateChapters(mHeroID, mCurrentChapter, mTotalChapters);

                                        }
                                    });

                                    choice3.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {

                                            mCurrentChapter = 322;
                                            mTotalChapters = mTotalChapters + 1;
                                            mHeroDBHelper.updateChapters(mHeroID, mCurrentChapter, mTotalChapters);

                                        }
                                    });

                                    choice4.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {

                                            mCurrentChapter = 35;
                                            mTotalChapters = mTotalChapters + 1;
                                            mHeroDBHelper.updateChapters(mHeroID, mCurrentChapter, mTotalChapters);

                                        }
                                    });

                                    choice5.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {

                                            mCurrentChapter = 95;
                                            mTotalChapters = mTotalChapters + 1;
                                            mHeroDBHelper.updateChapters(mHeroID, mCurrentChapter, mTotalChapters);

                                        }
                                    });

                                }

                                if (mCurrentChapter == 68) {

                                    mToolbar.setTitle(String.format(getString(R.string.current_chapter),
                                            String.valueOf(mTotalChapters)));

                                    choice1.setVisibility(View.VISIBLE);
                                    choice2.setVisibility(View.GONE);
                                    choice3.setVisibility(View.GONE);
                                    choice4.setVisibility(View.GONE);
                                    choice5.setVisibility(View.GONE);

                                    adventureContent.setText(getString(R.string.talisman_chapter68));
                                    choice1.setText(R.string.talisman_chapter68_choice1);

                                    choice1.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {

                                            //TODO Handle death savedgame
                                            Intent intent = new Intent(AdventureTalisman.this, HeroDeath.class);
                                            startActivity(intent);

                                        }
                                    });

                                    choice2.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {

                                            mCurrentChapter = 75;
                                            mTotalChapters = mTotalChapters + 1;
                                            mHeroDBHelper.updateChapters(mHeroID, mCurrentChapter, mTotalChapters);

                                        }
                                    });

                                }

                                if (mCurrentChapter == 70) {

                                    mToolbar.setTitle(String.format(getString(R.string.current_chapter),
                                            String.valueOf(mTotalChapters)));

                                    choice1.setVisibility(View.VISIBLE);
                                    choice2.setVisibility(View.GONE);
                                    choice3.setVisibility(View.GONE);
                                    choice4.setVisibility(View.GONE);
                                    choice5.setVisibility(View.GONE);

                                    adventureContent.setText(getString(R.string.talisman_chapter70));
                                    choice1.setText(R.string.talisman_chapter70_choice1);

                                    choice1.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {

                                            mCurrentChapter = 100;
                                            mTotalChapters = mTotalChapters + 1;
                                            mHeroDBHelper.updateChapters(mHeroID, mCurrentChapter, mTotalChapters);

                                        }
                                    });

                                    choice2.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {

                                            mCurrentChapter = 75;
                                            mTotalChapters = mTotalChapters + 1;
                                            mHeroDBHelper.updateChapters(mHeroID, mCurrentChapter, mTotalChapters);

                                        }
                                    });

                                }

                                if (mCurrentChapter == 75) {

                                    mToolbar.setTitle(String.format(getString(R.string.current_chapter),
                                            String.valueOf(mTotalChapters)));

                                    statsChange1.setVisibility(View.VISIBLE);
                                    statsChange2.setVisibility(View.GONE);

                                    mLuckLoss = 1;
                                    mLuckCurrent = mHeroDBHelper.currentLuck(mHeroID) + mLuckLoss;

                                    choice1.setVisibility(View.VISIBLE);
                                    choice2.setVisibility(View.GONE);
                                    choice3.setVisibility(View.GONE);
                                    choice4.setVisibility(View.GONE);
                                    choice5.setVisibility(View.GONE);

                                    adventureContent.setText(getString(R.string.talisman_chapter75));
                                    choice1.setText(R.string.talisman_chapter75_choice1);

                                    statsChange1.setText(String.format(getString(R.string.hero_luck_loss),
                                            String.valueOf(mLuckLoss), String.valueOf(mLuckCurrent - mLuckLoss)));

                                    choice1.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {

                                            mStuff3Gain = getString(R.string.talisman_stuff3);

                                            mGoldCoinsGain = 10;
                                            mGoldCoinsCurrent = mHeroDBHelper.currentGoldCoins(mHeroID);

                                            mHeroDBHelper.goldCoinGain(mHeroID, 10);
                                            mHeroDBHelper.stuff3TalismanGain(mHeroID, mStuff3Gain);

                                            mCurrentChapter = 114;
                                            mTotalChapters = mTotalChapters + 1;
                                            mHeroDBHelper.updateChapters(mHeroID, mCurrentChapter, mTotalChapters);

                                        }
                                    });

                                }

                                if (mCurrentChapter == 100) {

                                    mToolbar.setTitle(String.format(getString(R.string.current_chapter),
                                            String.valueOf(mTotalChapters)));

                                    statsChange1.setVisibility(View.VISIBLE);
                                    statsChange2.setVisibility(View.VISIBLE);

                                    choice1.setVisibility(View.VISIBLE);
                                    choice2.setVisibility(View.GONE);
                                    choice3.setVisibility(View.GONE);
                                    choice4.setVisibility(View.GONE);
                                    choice5.setVisibility(View.GONE);

                                    adventureContent.setText(getString(R.string.talisman_chapter100));
                                    choice1.setText(R.string.talisman_chapter100_choice1);

                                    mStuff3Gain = getString(R.string.talisman_stuff3);
                                    mGoldCoinsGain = 10;
                                    mGoldCoinsCurrent = mHeroDBHelper.currentGoldCoins(mHeroID) - mGoldCoinsGain;

                                    statsChange1.setText(String.format(getString(R.string.hero_gold_coins_gain),
                                            String.valueOf(mGoldCoinsGain), String.valueOf(mGoldCoinsCurrent + mGoldCoinsGain)));
                                    statsChange2.setText(String.format(getString(R.string.hero_stuff_gain),
                                            String.valueOf(mStuff3Gain)));

                                    choice1.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {

                                            mCurrentChapter = 125;
                                            mTotalChapters = mTotalChapters + 1;
                                            mHeroDBHelper.updateChapters(mHeroID, mCurrentChapter, mTotalChapters);

                                        }
                                    });

                                }

                                if (mCurrentChapter == 114) {

                                    mToolbar.setTitle(String.format(getString(R.string.current_chapter),
                                            String.valueOf(mTotalChapters)));

                                    statsChange1.setVisibility(View.VISIBLE);
                                    statsChange2.setVisibility(View.VISIBLE);

                                    choice1.setVisibility(View.VISIBLE);
                                    choice2.setVisibility(View.GONE);
                                    choice3.setVisibility(View.GONE);
                                    choice4.setVisibility(View.GONE);
                                    choice5.setVisibility(View.GONE);

                                    adventureContent.setText(getString(R.string.talisman_chapter114));
                                    choice1.setText(R.string.talisman_chapter114_choice1);

                                    mStuff3Gain = getString(R.string.talisman_stuff3);
                                    mGoldCoinsGain = 10;
                                    mGoldCoinsCurrent = mHeroDBHelper.currentGoldCoins(mHeroID) - mGoldCoinsGain;

                                    statsChange1.setText(String.format(getString(R.string.hero_gold_coins_gain),
                                            String.valueOf(mGoldCoinsGain), String.valueOf(mGoldCoinsCurrent + mGoldCoinsGain)));
                                    statsChange2.setText(String.format(getString(R.string.hero_stuff_gain),
                                            mStuff3Gain));

                                    choice1.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {

                                            mCurrentChapter = 125;
                                            mTotalChapters = mTotalChapters + 1;
                                            mHeroDBHelper.updateChapters(mHeroID, mCurrentChapter, mTotalChapters);

                                        }
                                    });

                                }

                                if (mCurrentChapter == 125) {

                                    mToolbar.setTitle(String.format(getString(R.string.current_chapter),
                                            String.valueOf(mTotalChapters)));

                                    statsChange1.setVisibility(View.GONE);
                                    statsChange2.setVisibility(View.GONE);

                                    choice1.setVisibility(View.VISIBLE);
                                    choice2.setVisibility(View.GONE);
                                    choice3.setVisibility(View.GONE);
                                    choice4.setVisibility(View.GONE);
                                    choice5.setVisibility(View.GONE);

                                    adventureContent.setText(getString(R.string.talisman_chapter125));
                                    choice1.setText(R.string.talisman_chapter125_choice1);

                                    choice1.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {

                                            mCurrentChapter = 185;
                                            mTotalChapters = mTotalChapters + 1;
                                            mHeroDBHelper.updateChapters(mHeroID, mCurrentChapter, mTotalChapters);

                                        }
                                    });

                                }

                                if (mCurrentChapter == 134) {

                                    mToolbar.setTitle(String.format(getString(R.string.current_chapter),
                                            String.valueOf(mTotalChapters)));

                                    choice1.setVisibility(View.VISIBLE);
                                    choice2.setVisibility(View.VISIBLE);
                                    choice3.setVisibility(View.VISIBLE);
                                    choice4.setVisibility(View.VISIBLE);
                                    choice5.setVisibility(View.GONE);

                                    adventureContent.setText(getString(R.string.talisman_chapter134));
                                    choice1.setText(R.string.talisman_chapter134_choice1);
                                    choice2.setText(R.string.talisman_chapter134_choice2);
                                    choice3.setText(R.string.talisman_chapter134_choice3);
                                    choice4.setText(R.string.talisman_chapter134_choice4);

                                    choice1.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {

                                            mCurrentChapter = 68;
                                            mTotalChapters = mTotalChapters + 1;
                                            mHeroDBHelper.updateChapters(mHeroID, mCurrentChapter, mTotalChapters);

                                        }
                                    });

                                    choice2.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {

                                            mCurrentChapter = 214;
                                            mTotalChapters = mTotalChapters + 1;
                                            mHeroDBHelper.updateChapters(mHeroID, mCurrentChapter, mTotalChapters);

                                        }
                                    });

                                    choice3.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {

                                            mCurrentChapter = 63;
                                            mTotalChapters = mTotalChapters + 1;
                                            mHeroDBHelper.updateChapters(mHeroID, mCurrentChapter, mTotalChapters);

                                        }
                                    });

                                    choice4.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {

                                            mGoldCoinsCurrent = mHeroDBHelper.currentGoldCoins(mHeroID);
                                            mGoldCoinsLoss = mGoldCoinsCurrent;
                                            mHeroDBHelper.goldCoinsLoss(mHeroID, mGoldCoinsLoss);

                                            mCurrentChapter = 148;
                                            mTotalChapters = mTotalChapters + 1;
                                            mHeroDBHelper.updateChapters(mHeroID, mCurrentChapter, mTotalChapters);

                                        }
                                    });

                                }

                                if (mCurrentChapter == 135) {

                                    mToolbar.setTitle(String.format(getString(R.string.current_chapter),
                                            String.valueOf(mTotalChapters)));

                                    statsChange1.setVisibility(View.VISIBLE);
                                    statsChange2.setVisibility(View.GONE);
                                    statsChange3.setVisibility(View.GONE);

                                    choice1.setVisibility(View.VISIBLE);
                                    choice2.setVisibility(View.GONE);
                                    choice3.setVisibility(View.GONE);
                                    choice4.setVisibility(View.GONE);
                                    choice5.setVisibility(View.GONE);

                                    adventureContent.setText(getString(R.string.talisman_chapter135));
                                    choice1.setText(R.string.talisman_chapter135_choice1);

                                    mAbilityLoss = 1;
                                    mAbilityCurrent = mHeroDBHelper.currentAbility(mHeroID) - mAbilityLoss;

                                    statsChange1.setText(String.format(getString(R.string.hero_ability_loss),
                                            String.valueOf(mAbilityLoss), String.valueOf(mAbilityCurrent)));

                                    choice1.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {

                                            mCurrentChapter = 270;
                                            mTotalChapters = mTotalChapters + 1;
                                            mHeroDBHelper.updateChapters(mHeroID, mCurrentChapter, mTotalChapters);

                                        }
                                    });

                                }

                                if (mCurrentChapter == 148) {

                                    mToolbar.setTitle(String.format(getString(R.string.current_chapter),
                                            String.valueOf(mTotalChapters)));

                                    statsChange1.setVisibility(View.VISIBLE);

                                    choice1.setVisibility(View.VISIBLE);
                                    choice2.setVisibility(View.GONE);
                                    choice3.setVisibility(View.GONE);
                                    choice4.setVisibility(View.GONE);
                                    choice5.setVisibility(View.GONE);

                                    adventureContent.setText(getString(R.string.talisman_chapter148));
                                    choice1.setText(R.string.talisman_chapter148_choice1);

                                    statsChange1.setText(getString(R.string.hero_gold_coins_loss_full));

                                    choice1.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {

                                            mCurrentChapter = 203;
                                            mTotalChapters = mTotalChapters + 1;
                                            mHeroDBHelper.updateChapters(mHeroID, mCurrentChapter, mTotalChapters);

                                        }
                                    });

                                }

                                if (mCurrentChapter == 159) {

                                    mToolbar.setTitle(String.format(getString(R.string.current_chapter),
                                            String.valueOf(mTotalChapters)));

                                    statsChange1.setVisibility(View.GONE);
                                    statsChange2.setVisibility(View.GONE);
                                    statsChange3.setVisibility(View.GONE);

                                    choice1.setVisibility(View.VISIBLE);
                                    choice2.setVisibility(View.VISIBLE);
                                    choice3.setVisibility(View.GONE);
                                    choice4.setVisibility(View.GONE);
                                    choice5.setVisibility(View.GONE);

                                    adventureContent.setText(getString(R.string.talisman_chapter159));
                                    choice1.setText(R.string.talisman_chapter159_choice1);
                                    choice2.setText(R.string.talisman_chapter159_choice2);

                                    choice1.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {

                                            Intent intent = new Intent(AdventureTalisman.this, PlayLuck.class);
                                            intent.putExtra("HeroID", mHeroID);
                                            intent.putExtra("CurrentChapter", mCurrentChapter);
                                            intent.putExtra("TotalChapters", mTotalChapters);
                                            startActivity(intent);

                                        }
                                    });

                                    choice2.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {

                                            mMealsLoss = 1;
                                            mHeroDBHelper.mealLoss(mHeroID, mMealsLoss);

                                            mCurrentChapter = 384;
                                            mTotalChapters = mTotalChapters + 1;
                                            mHeroDBHelper.updateChapters(mHeroID, mCurrentChapter, mTotalChapters);

                                        }
                                    });

                                }

                                if (mCurrentChapter == 185) {

                                    mToolbar.setTitle(String.format(getString(R.string.current_chapter),
                                            String.valueOf(mTotalChapters)));

                                    statsChange1.setVisibility(View.GONE);
                                    statsChange2.setVisibility(View.GONE);

                                    choice1.setVisibility(View.VISIBLE);
                                    choice2.setVisibility(View.VISIBLE);
                                    choice3.setVisibility(View.GONE);
                                    choice4.setVisibility(View.GONE);
                                    choice5.setVisibility(View.GONE);

                                    adventureContent.setText(getString(R.string.talisman_chapter185));
                                    choice1.setText(R.string.talisman_chapter185_choice1);
                                    choice2.setText(R.string.talisman_chapter185_choice2);

                                    choice1.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {

                                            mCurrentChapter = 256;
                                            mTotalChapters = mTotalChapters + 1;
                                            mHeroDBHelper.updateChapters(mHeroID, mCurrentChapter, mTotalChapters);

                                        }
                                    });

                                    choice2.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {

                                            mCurrentChapter = 134;
                                            mTotalChapters = mTotalChapters + 1;
                                            mHeroDBHelper.updateChapters(mHeroID, mCurrentChapter, mTotalChapters);

                                        }
                                    });

                                }

                                if (mCurrentChapter == 203) {

                                    mToolbar.setTitle(String.format(getString(R.string.current_chapter),
                                            String.valueOf(mTotalChapters)));

                                    statsChange1.setVisibility(View.GONE);

                                    choice1.setVisibility(View.VISIBLE);
                                    choice2.setVisibility(View.GONE);
                                    choice3.setVisibility(View.GONE);
                                    choice4.setVisibility(View.GONE);
                                    choice5.setVisibility(View.GONE);

                                    adventureContent.setText(getString(R.string.talisman_chapter203));
                                    choice1.setText(R.string.hero_play_luck);

                                    choice1.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {

                                            Intent intent = new Intent(AdventureTalisman.this, PlayLuck.class);
                                            intent.putExtra("HeroID", mHeroID);
                                            intent.putExtra("CurrentChapter", mCurrentChapter);
                                            intent.putExtra("TotalChapters", mTotalChapters);
                                            startActivity(intent);

                                        }
                                    });
                                }

                                if (mCurrentChapter == 214) {

                                    mToolbar.setTitle(String.format(getString(R.string.current_chapter),
                                            String.valueOf(mTotalChapters)));

                                    choice1.setVisibility(View.VISIBLE);
                                    choice2.setVisibility(View.GONE);
                                    choice3.setVisibility(View.GONE);
                                    choice4.setVisibility(View.GONE);
                                    choice5.setVisibility(View.GONE);

                                    adventureContent.setText(getString(R.string.talisman_chapter214));
                                    choice1.setText(R.string.talisman_chapter214_choice1);

                                    choice1.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {

                                            //TODO Handle death savedgame
                                            Intent intent = new Intent(AdventureTalisman.this, HeroDeath.class);
                                            startActivity(intent);

                                        }
                                    });

                                }

                                if (mCurrentChapter == 218) {

                                    mToolbar.setTitle(String.format(getString(R.string.current_chapter),
                                            String.valueOf(mTotalChapters)));

                                    choice1.setVisibility(View.VISIBLE);
                                    choice2.setVisibility(View.GONE);
                                    choice3.setVisibility(View.GONE);
                                    choice4.setVisibility(View.GONE);
                                    choice5.setVisibility(View.GONE);

                                    adventureContent.setText(getString(R.string.talisman_chapter218));
                                    choice1.setText(R.string.talisman_chapter218_choice1);

                                    choice1.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {

                                            mCurrentChapter = 159;
                                            mTotalChapters = mTotalChapters + 1;
                                            mHeroDBHelper.updateChapters(mHeroID, mCurrentChapter, mTotalChapters);
                                        }
                                    });

                                }

                                if (mCurrentChapter == 227) {

                                    mToolbar.setTitle(String.format(getString(R.string.current_chapter),
                                            String.valueOf(mTotalChapters)));

                                    choice1.setVisibility(View.VISIBLE);
                                    choice2.setVisibility(View.GONE);
                                    choice3.setVisibility(View.GONE);
                                    choice4.setVisibility(View.GONE);
                                    choice5.setVisibility(View.GONE);

                                    adventureContent.setText(getString(R.string.talisman_chapter227));
                                    choice1.setText(R.string.talisman_chapter227_choice1);

                                    choice1.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {

                                            mCurrentChapter = 203;
                                            mTotalChapters = mTotalChapters + 1;
                                            mHeroDBHelper.updateChapters(mHeroID, mCurrentChapter, mTotalChapters);

                                        }
                                    });

                                }

                                if (mCurrentChapter == 230) {

                                    mToolbar.setTitle(String.format(getString(R.string.current_chapter),
                                            String.valueOf(mTotalChapters)));

                                    choice1.setVisibility(View.VISIBLE);
                                    choice2.setVisibility(View.GONE);
                                    choice3.setVisibility(View.GONE);
                                    choice4.setVisibility(View.GONE);
                                    choice5.setVisibility(View.GONE);

                                    adventureContent.setText(getString(R.string.talisman_chapter230));
                                    choice1.setText(R.string.talisman_chapter230_choice1);

                                    choice1.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {

                                        //TODO Fight !

                                        }
                                    });

                                }

                                if (mCurrentChapter == 232) {

                                    mToolbar.setTitle(String.format(getString(R.string.current_chapter),
                                            String.valueOf(mTotalChapters)));

                                    choice1.setVisibility(View.VISIBLE);
                                    choice2.setVisibility(View.GONE);
                                    choice3.setVisibility(View.GONE);
                                    choice4.setVisibility(View.GONE);
                                    choice5.setVisibility(View.GONE);

                                    adventureContent.setText(getString(R.string.talisman_chapter232));
                                    choice1.setText(R.string.talisman_chapter232_choice1);

                                    choice1.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {

                                        //TODO Fight !

                                        }
                                    });

                                }

                                if (mCurrentChapter == 247) {

                                    mToolbar.setTitle(String.format(getString(R.string.current_chapter),
                                            String.valueOf(mTotalChapters)));

                                    choice1.setVisibility(View.VISIBLE);
                                    choice2.setVisibility(View.GONE);
                                    choice3.setVisibility(View.GONE);
                                    choice4.setVisibility(View.GONE);
                                    choice5.setVisibility(View.GONE);

                                    adventureContent.setText(getString(R.string.talisman_chapter247));
                                    choice1.setText(R.string.talisman_chapter247_choice1);

                                    mStuff3Gain = getString(R.string.talisman_stuff3);
                                    mGoldCoinsGain = 10;
                                    mGoldCoinsCurrent = mHeroDBHelper.currentGoldCoins(mHeroID) - mGoldCoinsGain;

                                    choice1.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {

                                            mStuff3Gain = getString(R.string.talisman_stuff3);

                                            mGoldCoinsGain = 10;

                                            mHeroDBHelper.goldCoinGain(mHeroID, 10);
                                            mHeroDBHelper.stuff3TalismanGain(mHeroID, mStuff3Gain);

                                            mCurrentChapter = 100;
                                            mTotalChapters = mTotalChapters + 1;
                                            mHeroDBHelper.updateChapters(mHeroID, mCurrentChapter, mTotalChapters);

                                        }
                                    });

                                }

                                if (mCurrentChapter == 256) {

                                    mToolbar.setTitle(String.format(getString(R.string.current_chapter),
                                            String.valueOf(mTotalChapters)));

                                    choice1.setVisibility(View.VISIBLE);
                                    choice2.setVisibility(View.VISIBLE);
                                    choice3.setVisibility(View.VISIBLE);
                                    choice4.setVisibility(View.GONE);
                                    choice5.setVisibility(View.GONE);

                                    adventureContent.setText(getString(R.string.talisman_chapter256));
                                    choice1.setText(R.string.talisman_chapter256_choice1);
                                    choice2.setText(R.string.talisman_chapter256_choice2);
                                    choice3.setText(R.string.talisman_chapter256_choice3);

                                    choice1.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {

                                            mCurrentChapter = 218;
                                            mTotalChapters = mTotalChapters + 1;
                                            mHeroDBHelper.updateChapters(mHeroID, mCurrentChapter, mTotalChapters);

                                        }
                                    });

                                    choice2.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {

                                            mLuckGain = 1;
                                            mLuckCurrent = mHeroDBHelper.currentLuck(mHeroID);
                                            mHeroDBHelper.currentLuckGain(mHeroID, mLuckGain);

                                            mCurrentChapter = 52;
                                            mTotalChapters = mTotalChapters + 1;
                                            mHeroDBHelper.updateChapters(mHeroID, mCurrentChapter, mTotalChapters);

                                        }
                                    });

                                    choice3.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {

                                            mCurrentChapter = 232;
                                            mTotalChapters = mTotalChapters + 1;
                                            mHeroDBHelper.updateChapters(mHeroID, mCurrentChapter, mTotalChapters);

                                        }
                                    });

                                }

                                if (mCurrentChapter == 267) {

                                    mToolbar.setTitle(String.format(getString(R.string.current_chapter),
                                            String.valueOf(mTotalChapters)));

                                    statsChange1.setVisibility(View.GONE);
                                    statsChange2.setVisibility(View.GONE);
                                    statsChange3.setVisibility(View.GONE);

                                    choice1.setVisibility(View.VISIBLE);
                                    choice2.setVisibility(View.GONE);
                                    choice3.setVisibility(View.GONE);
                                    choice4.setVisibility(View.GONE);
                                    choice5.setVisibility(View.GONE);

                                    adventureContent.setText(getString(R.string.talisman_chapter267));
                                    choice1.setText(R.string.talisman_chapter267_choice1);

                                    choice1.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {

                                            mCurrentChapter = 270;
                                            mTotalChapters = mTotalChapters + 1;
                                            mHeroDBHelper.updateChapters(mHeroID, mCurrentChapter, mTotalChapters);

                                        }
                                    });

                                }

                                if (mCurrentChapter == 270) {

                                    mToolbar.setTitle(String.format(getString(R.string.current_chapter),
                                            String.valueOf(mTotalChapters)));

                                    choice1.setVisibility(View.VISIBLE);
                                    choice2.setVisibility(View.VISIBLE);
                                    choice3.setVisibility(View.GONE);
                                    choice4.setVisibility(View.GONE);
                                    choice5.setVisibility(View.GONE);

                                    adventureContent.setText(getString(R.string.talisman_chapter270));
                                    choice1.setText(R.string.talisman_chapter270_choice1);
                                    choice2.setText(R.string.talisman_chapter270_choice2);

                                    choice1.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {

                                            mCurrentChapter = 230;
                                            mTotalChapters = mTotalChapters + 1;
                                            mHeroDBHelper.updateChapters(mHeroID, mCurrentChapter, mTotalChapters);

                                        }
                                    });

                                    choice2.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {

                                            mCurrentChapter = 65;
                                            mTotalChapters = mTotalChapters + 1;
                                            mHeroDBHelper.updateChapters(mHeroID, mCurrentChapter, mTotalChapters);

                                        }
                                    });

                                }

                                if (mCurrentChapter == 282) {

                                    mToolbar.setTitle(String.format(getString(R.string.current_chapter),
                                            String.valueOf(mTotalChapters)));

                                    choice1.setVisibility(View.VISIBLE);
                                    choice2.setVisibility(View.GONE);
                                    choice3.setVisibility(View.GONE);
                                    choice4.setVisibility(View.GONE);
                                    choice5.setVisibility(View.GONE);

                                    adventureContent.setText(getString(R.string.talisman_chapter282));
                                    choice1.setText(R.string.talisman_chapter282_choice1);

                                    choice1.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {

                                            //TODO Fight !

                                        }
                                    });

                                }

                                if (mCurrentChapter == 319) {

                                    mToolbar.setTitle(String.format(getString(R.string.current_chapter),
                                            String.valueOf(mTotalChapters)));

                                    choice1.setVisibility(View.VISIBLE);
                                    choice2.setVisibility(View.GONE);
                                    choice3.setVisibility(View.GONE);
                                    choice4.setVisibility(View.GONE);
                                    choice5.setVisibility(View.GONE);

                                    adventureContent.setText(getString(R.string.talisman_chapter319));
                                    choice1.setText(R.string.talisman_chapter319_choice1);

                                    choice1.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {

                                            //TODO Fight !

                                        }
                                    });

                                }

                                if (mCurrentChapter == 384) {

                                    mToolbar.setTitle(String.format(getString(R.string.current_chapter),
                                            String.valueOf(mTotalChapters)));

                                    statsChange1.setVisibility(View.VISIBLE);
                                    statsChange2.setVisibility(View.GONE);
                                    statsChange3.setVisibility(View.GONE);

                                    choice1.setVisibility(View.VISIBLE);
                                    choice2.setVisibility(View.GONE);
                                    choice3.setVisibility(View.GONE);
                                    choice4.setVisibility(View.GONE);
                                    choice5.setVisibility(View.GONE);

                                    adventureContent.setText(getString(R.string.talisman_chapter384));
                                    choice1.setText(R.string.talisman_chapter384_choice1);

                                    mMealsUse = 1;
                                    mMealsCurrent = mHeroDBHelper.currentMeals(mHeroID) + 1;

                                    statsChange1.setText(String.format(getString(R.string.hero_meals_loss_no_use),
                                            String.valueOf(mMealsUse), String.valueOf(mMealsCurrent)));

                                    choice1.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {

                                            mCurrentChapter = 65;
                                            mTotalChapters = mTotalChapters + 1;
                                            mHeroDBHelper.updateChapters(mHeroID, mCurrentChapter, mTotalChapters);

                                        }
                                    });

                                }


                            }
                        });
                    }
                } catch (InterruptedException ignored) {
                }
            }
        };

        runningAdventure.start();

    }


    // Drawer Menu - back to main menu
    @Override
    public void onBackPressed() {

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            Intent intent = new Intent(AdventureTalisman.this, MainMenu.class);
            startActivity(intent);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_adventure_sheet) {
            Intent intent = new Intent(getApplicationContext(), AdventureSheet.class);
            intent.putExtra("HeroID", String.valueOf(mHeroID));
            intent.putExtra("CurrentChapter", mCurrentChapter);
            intent.putExtra("TotalChapters", mTotalChapters);
            startActivity(intent);
        } else if (id == R.id.nav_current_adventure) {
            Intent intent = new Intent(getApplicationContext(), AdventureTalisman.class);
            intent.putExtra("HeroID", String.valueOf(mHeroID));
            intent.putExtra("CurrentChapter", mCurrentChapter);
            intent.putExtra("TotalChapters", mTotalChapters);
            startActivity(intent);
        } else if (id == R.id.nav_statistics) {
            Intent intent = new Intent(getApplicationContext(), MainMenu.class);
            startActivity(intent);
        } else if (id == R.id.nav_menu) {
            Intent intent = new Intent(getApplicationContext(), MainMenu.class);
            startActivity(intent);
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
