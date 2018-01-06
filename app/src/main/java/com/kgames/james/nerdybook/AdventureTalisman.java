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
    int mLuckLoss;
    int mLuckCurrent;
    int mGoldCoinsLoss;
    int mGoldCoinsCurrent;
    int mGoldCoinsGain;
    String mStuff3Gain;

    boolean mLuckOfFate;

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
        final TextView statsLoss1 = findViewById(R.id.stats_loss1);
        final TextView statsLoss2 = findViewById(R.id.stats_loss2);
        final TextView statsLoss3 = findViewById(R.id.stats_loss3);

        //adventureContent.setTypeface(mainFont);

        final Button choice1 = findViewById(R.id.choice_1);
        final Button choice2 = findViewById(R.id.choice_2);
        final Button choice3 = findViewById(R.id.choice_3);
        final Button choice4 = findViewById(R.id.choice_4);

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

                                    statsLoss1.setVisibility(View.VISIBLE);
                                    statsLoss2.setVisibility(View.VISIBLE);

                                    mAbilityLoss = 1;
                                    mStaminaLoss = 2;
                                    mAbilityCurrent = mHeroDBHelper.currentAbility(mHeroID);
                                    mStaminaCurrent = mHeroDBHelper.currentStamina(mHeroID);

                                    if (mStaminaCurrent - mStaminaLoss <= 0) {

                                        statsLoss1.setText(getString(R.string.hero_stamina_null));

                                        choice1.setVisibility(View.VISIBLE);
                                        choice2.setVisibility(View.GONE);
                                        choice3.setVisibility(View.GONE);
                                        choice4.setVisibility(View.GONE);

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

                                        adventureContent.setText(getString(R.string.talisman_chapter5));
                                        choice1.setText(R.string.talisman_chapter5_choice1);

                                        statsLoss1.setText(String.format(getString(R.string.hero_ability_loss),
                                                String.valueOf(mAbilityLoss), String.valueOf(mAbilityCurrent - mAbilityLoss)));

                                        statsLoss2.setText(String.format(getString(R.string.hero_stamina_loss),
                                                String.valueOf(mStaminaLoss), String.valueOf(mStaminaCurrent - mStaminaLoss)));

                                        choice1.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {

                                                mHeroDBHelper.currentAbilityLoss(mHeroID, mAbilityLoss);
                                                mHeroDBHelper.currentStaminaLoss(mHeroID, mStaminaLoss);

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

                                    statsLoss1.setVisibility(View.GONE);

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

                                            mCurrentChapter = 75;
                                            mTotalChapters = mTotalChapters + 1;
                                            mHeroDBHelper.updateChapters(mHeroID, mCurrentChapter, mTotalChapters);

                                        }
                                    });

                                    choice4.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {

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

                                    adventureContent.setText(getString(R.string.talisman_chapter17));
                                    choice1.setText(R.string.talisman_chapter17_choice1);
                                    choice2.setText(R.string.talisman_chapter17_choice2);

                                    choice1.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {

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

                                if (mCurrentChapter == 41) {


                                    setSupportActionBar(mToolbar);
                                    mToolbar.setTitle(String.format(getString(R.string.current_chapter),
                                            String.valueOf(mTotalChapters)));

                                    statsLoss1.setVisibility(View.VISIBLE);

                                    mStaminaLoss = 2;
                                    mStaminaCurrent = mHeroDBHelper.currentStamina(mHeroID);

                                    if (mStaminaCurrent - mStaminaLoss <= 0) {

                                        statsLoss1.setText(getString(R.string.hero_stamina_null));

                                        choice1.setVisibility(View.VISIBLE);
                                        choice2.setVisibility(View.GONE);
                                        choice3.setVisibility(View.GONE);
                                        choice4.setVisibility(View.GONE);

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

                                        statsLoss1.setText(String.format(getString(R.string.hero_stamina_loss),
                                                String.valueOf(mStaminaLoss), String.valueOf(mStaminaCurrent - mStaminaLoss)));

                                        choice1.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {

                                                mHeroDBHelper.currentStaminaLoss(mHeroID, mStaminaLoss);

                                                mCurrentChapter = 13;
                                                mTotalChapters = mTotalChapters + 1;
                                                mHeroDBHelper.updateChapters(mHeroID, mCurrentChapter, mTotalChapters);

                                            }
                                        });
                                    }

                                }

                                if (mCurrentChapter == 60) {

                                    mToolbar.setTitle(String.format(getString(R.string.current_chapter),
                                            String.valueOf(mTotalChapters)));

                                    choice1.setVisibility(View.VISIBLE);
                                    choice2.setVisibility(View.VISIBLE);
                                    choice3.setVisibility(View.GONE);
                                    choice4.setVisibility(View.GONE);

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

                                if (mCurrentChapter == 70) {

                                    mToolbar.setTitle(String.format(getString(R.string.current_chapter),
                                            String.valueOf(mTotalChapters)));

                                    choice1.setVisibility(View.VISIBLE);
                                    choice2.setVisibility(View.GONE);
                                    choice3.setVisibility(View.GONE);
                                    choice4.setVisibility(View.GONE);

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

                                    statsLoss1.setVisibility(View.VISIBLE);
                                    statsLoss2.setVisibility(View.GONE);

                                    mLuckLoss = 1;
                                    mLuckCurrent = mHeroDBHelper.currentLuck(mHeroID);

                                    choice1.setVisibility(View.VISIBLE);
                                    choice2.setVisibility(View.GONE);
                                    choice3.setVisibility(View.GONE);
                                    choice4.setVisibility(View.GONE);

                                    adventureContent.setText(getString(R.string.talisman_chapter75));
                                    choice1.setText(R.string.talisman_chapter75_choice1);

                                    statsLoss1.setText(String.format(getString(R.string.hero_luck_loss),
                                            String.valueOf(mLuckLoss), String.valueOf(mLuckCurrent - mLuckLoss)));

                                    choice1.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {

                                            mHeroDBHelper.currentLuckLoss(mHeroID, mLuckLoss);

                                            mCurrentChapter = 114;
                                            mTotalChapters = mTotalChapters + 1;
                                            mHeroDBHelper.updateChapters(mHeroID, mCurrentChapter, mTotalChapters);

                                        }
                                    });

                                }

                                if (mCurrentChapter == 100) {

                                    mToolbar.setTitle(String.format(getString(R.string.current_chapter),
                                            String.valueOf(mTotalChapters)));

                                    statsLoss1.setVisibility(View.VISIBLE);
                                    statsLoss2.setVisibility(View.VISIBLE);

                                    choice1.setVisibility(View.VISIBLE);
                                    choice2.setVisibility(View.GONE);
                                    choice3.setVisibility(View.GONE);
                                    choice4.setVisibility(View.GONE);

                                    adventureContent.setText(getString(R.string.talisman_chapter100));
                                    choice1.setText(R.string.talisman_chapter100_choice1);

                                    mGoldCoinsGain = 10;
                                    mGoldCoinsCurrent = mHeroDBHelper.currentGoldCoins(mHeroID);

                                    statsLoss1.setText(String.format(getString(R.string.hero_gold_coins_gain),
                                            String.valueOf(mGoldCoinsGain), String.valueOf(mGoldCoinsCurrent + mGoldCoinsGain)));
                                    statsLoss2.setText(String.format(getString(R.string.hero_stuff_gain),
                                            String.valueOf(mStuff3Gain)));

                                    choice1.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {

                                            mHeroDBHelper.goldCoinGain(mHeroID, 10);
                                            mHeroDBHelper.stuff3TalismanGain(mHeroID, mStuff3Gain);

                                            mCurrentChapter = 125;
                                            mTotalChapters = mTotalChapters + 1;
                                            mHeroDBHelper.updateChapters(mHeroID, mCurrentChapter, mTotalChapters);

                                        }
                                    });

                                }

                                if (mCurrentChapter == 114) {

                                    mToolbar.setTitle(String.format(getString(R.string.current_chapter),
                                            String.valueOf(mTotalChapters)));

                                    mStuff3Gain = getString(R.string.talisman_stuff3);

                                    statsLoss1.setVisibility(View.VISIBLE);
                                    statsLoss2.setVisibility(View.VISIBLE);

                                    choice1.setVisibility(View.VISIBLE);
                                    choice2.setVisibility(View.GONE);
                                    choice3.setVisibility(View.GONE);
                                    choice4.setVisibility(View.GONE);

                                    adventureContent.setText(getString(R.string.talisman_chapter114));
                                    choice1.setText(R.string.talisman_chapter114_choice1);

                                    mGoldCoinsGain = 10;
                                    mGoldCoinsCurrent = mHeroDBHelper.currentGoldCoins(mHeroID);

                                    statsLoss1.setText(String.format(getString(R.string.hero_gold_coins_gain),
                                            String.valueOf(mGoldCoinsGain), String.valueOf(mGoldCoinsCurrent + mGoldCoinsGain)));
                                    statsLoss2.setText(String.format(getString(R.string.hero_stuff_gain),
                                            mStuff3Gain));

                                    choice1.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {

                                            mHeroDBHelper.goldCoinGain(mHeroID, 10);
                                            mHeroDBHelper.stuff3TalismanGain(mHeroID, mStuff3Gain);

                                            mCurrentChapter = 125;
                                            mTotalChapters = mTotalChapters + 1;
                                            mHeroDBHelper.updateChapters(mHeroID, mCurrentChapter, mTotalChapters);

                                        }
                                    });

                                }

                                if (mCurrentChapter == 125) {

                                    mToolbar.setTitle(String.format(getString(R.string.current_chapter),
                                            String.valueOf(mTotalChapters)));

                                    statsLoss1.setVisibility(View.GONE);
                                    statsLoss2.setVisibility(View.GONE);

                                    choice1.setVisibility(View.VISIBLE);
                                    choice2.setVisibility(View.GONE);
                                    choice3.setVisibility(View.GONE);
                                    choice4.setVisibility(View.GONE);

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

                                            mCurrentChapter = 148;
                                            mTotalChapters = mTotalChapters + 1;
                                            mHeroDBHelper.updateChapters(mHeroID, mCurrentChapter, mTotalChapters);

                                        }
                                    });

                                }

                                if (mCurrentChapter == 148) {

                                    mToolbar.setTitle(String.format(getString(R.string.current_chapter),
                                            String.valueOf(mTotalChapters)));

                                    statsLoss1.setVisibility(View.VISIBLE);

                                    choice1.setVisibility(View.VISIBLE);
                                    choice2.setVisibility(View.GONE);
                                    choice3.setVisibility(View.GONE);
                                    choice4.setVisibility(View.GONE);

                                    adventureContent.setText(getString(R.string.talisman_chapter148));
                                    choice1.setText(R.string.talisman_chapter148_choice1);

                                    mGoldCoinsCurrent = mHeroDBHelper.currentGoldCoins(mHeroID);
                                    mGoldCoinsLoss = mGoldCoinsCurrent;

                                    statsLoss1.setText(getString(R.string.hero_gold_coins_loss_full));

                                    choice1.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {

                                            mHeroDBHelper.goldCoinsLoss(mHeroID, mGoldCoinsLoss);

                                            mCurrentChapter = 203;
                                            mTotalChapters = mTotalChapters + 1;
                                            mHeroDBHelper.updateChapters(mHeroID, mCurrentChapter, mTotalChapters);

                                        }
                                    });

                                }

                                if (mCurrentChapter == 185) {

                                    mToolbar.setTitle(String.format(getString(R.string.current_chapter),
                                            String.valueOf(mTotalChapters)));

                                    statsLoss1.setVisibility(View.GONE);
                                    statsLoss2.setVisibility(View.GONE);

                                    choice1.setVisibility(View.VISIBLE);
                                    choice2.setVisibility(View.VISIBLE);
                                    choice3.setVisibility(View.GONE);
                                    choice4.setVisibility(View.GONE);

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

                                    statsLoss1.setVisibility(View.GONE);

                                    choice1.setVisibility(View.VISIBLE);
                                    choice2.setVisibility(View.GONE);
                                    choice3.setVisibility(View.GONE);
                                    choice4.setVisibility(View.GONE);

                                    adventureContent.setText(getString(R.string.talisman_chapter203));
                                    choice1.setText(R.string.hero_play_luck);

                                    choice1.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {

                                            boolean luckOfFate = mHeroDBHelper.isHeroLucky(mHeroID);

                                            if (luckOfFate) {

                                                mToolbar.setTitle(String.format(getString(R.string.current_chapter),
                                                        String.valueOf(mTotalChapters)));

                                                choice1.setVisibility(View.VISIBLE);
                                                choice2.setVisibility(View.GONE);
                                                choice3.setVisibility(View.GONE);
                                                choice4.setVisibility(View.GONE);

                                                adventureContent.setText(getString(R.string.talisman_chapter203));

                                                choice1.setText(R.string.talisman_chapter203_choice1);
                                                choice1.setOnClickListener(new View.OnClickListener() {
                                                    @Override
                                                    public void onClick(View view) {

                                                        mCurrentChapter = 36;
                                                        mTotalChapters = mTotalChapters + 1;
                                                        mHeroDBHelper.updateChapters(mHeroID, mCurrentChapter, mTotalChapters);
                                                    }
                                                });

                                            } else {

                                                mToolbar.setTitle(String.format(getString(R.string.current_chapter),
                                                        String.valueOf(mTotalChapters)));

                                                choice1.setVisibility(View.VISIBLE);
                                                choice2.setVisibility(View.GONE);
                                                choice3.setVisibility(View.GONE);
                                                choice4.setVisibility(View.GONE);

                                                adventureContent.setText(getString(R.string.talisman_chapter203));

                                                choice1.setText(R.string.talisman_chapter203_choice2);
                                                choice1.setOnClickListener(new View.OnClickListener() {
                                                    @Override
                                                    public void onClick(View v) {

                                                        mCurrentChapter = 319;
                                                        mTotalChapters = mTotalChapters + 1;
                                                        mHeroDBHelper.updateChapters(mHeroID, mCurrentChapter, mTotalChapters);
                                                    }
                                                });

                                            }
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

                                    adventureContent.setText(getString(R.string.talisman_chapter214));
                                    choice1.setText(R.string.talisman_chapter214_choice1);

                                    choice1.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {

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

                                if (mCurrentChapter == 247) {

                                    mToolbar.setTitle(String.format(getString(R.string.current_chapter),
                                            String.valueOf(mTotalChapters)));

                                    choice1.setVisibility(View.VISIBLE);
                                    choice2.setVisibility(View.GONE);
                                    choice3.setVisibility(View.GONE);
                                    choice4.setVisibility(View.GONE);

                                    adventureContent.setText(getString(R.string.talisman_chapter247));
                                    choice1.setText(R.string.talisman_chapter247_choice1);

                                    choice1.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {

                                            mCurrentChapter = 100;
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


    // Drawer Menu
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
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
            Intent intent = new Intent(getApplicationContext(), AdventureSheetActivity.class);
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
