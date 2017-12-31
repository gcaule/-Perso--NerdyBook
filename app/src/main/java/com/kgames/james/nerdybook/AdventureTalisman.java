package com.kgames.james.nerdybook;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.kgames.james.nerdybook.Hero.HeroDBHelper;

public class AdventureTalisman extends AppCompatActivity {

    HeroDBHelper mHeroDBHelper;

    String mHeroID;
    int mCurrentChapter;
    int mTotalChapters;
    int mStatLoss;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adventure_talisman);

        mHeroDBHelper = new HeroDBHelper(AdventureTalisman.this);

        mHeroID = getIntent().getExtras().getString("HeroID");
        mCurrentChapter = getIntent().getExtras().getInt("CurrentChapter");
        mTotalChapters = getIntent().getExtras().getInt("TotalChapters");

        //Typeface mainFont = Typeface.createFromAsset(getAssets(), "fonts/Dosmilcatorce.ttf");

        final TextView adventureContent = findViewById(R.id.adventure_content);
        final TextView statsLoss = findViewById(R.id.stats_loss);

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

                                    choice1.setVisibility(View.VISIBLE);
                                    choice2.setVisibility(View.VISIBLE);
                                    choice3.setVisibility(View.VISIBLE);
                                    choice4.setVisibility(View.VISIBLE);

                                    statsLoss.setVisibility(View.GONE);

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

                                    choice1.setVisibility(View.VISIBLE);
                                    choice2.setVisibility(View.GONE);
                                    choice3.setVisibility(View.GONE);
                                    choice4.setVisibility(View.GONE);

                                    statsLoss.setVisibility(View.VISIBLE);

                                    mStatLoss = 2;
                                    statsLoss.setText(String.format(getString(R.string.hero_stamina_loss), String.valueOf(mStatLoss)));

                                    adventureContent.setText(getString(R.string.talisman_chapter41));
                                    choice1.setText(R.string.talisman_chapter41_choice1);

                                    choice1.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {

                                            mHeroDBHelper.currentStaminaLoss(mHeroID, mStatLoss);

                                            mCurrentChapter = 13;
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
}
