package com.kgames.james.nerdybook;

import android.content.Intent;
import android.support.annotation.NonNull;
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

                                    if (mStaminaCurrent - mStaminaLoss < 0) {

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

                                        statsLoss1.setText(String.format(getString(R.string.hero_stamina_loss),
                                                String.valueOf(mStaminaLoss), String.valueOf(mStaminaCurrent - mStaminaLoss)));

                                        adventureContent.setText(getString(R.string.talisman_chapter41));
                                        choice1.setText(R.string.talisman_chapter41_choice1);

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
