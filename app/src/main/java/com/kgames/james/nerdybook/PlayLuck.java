package com.kgames.james.nerdybook;

import android.content.Intent;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.kgames.james.nerdybook.Hero.HeroDBHelper;

public class PlayLuck extends AppCompatActivity {

    HeroDBHelper mHeroDBHelper;

    String mHeroID;
    int mCurrentChapter;
    int mTotalChapters;
    int mLuckCurrent;

    int mAbilityLoss;

    boolean mLuckOfFate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_luck);

        mHeroDBHelper = new HeroDBHelper(PlayLuck.this);

        mHeroID = getIntent().getExtras().getString("HeroID");
        mCurrentChapter = getIntent().getExtras().getInt("CurrentChapter");
        mTotalChapters = getIntent().getExtras().getInt("TotalChapters");

        final Button playYourLuck = findViewById(R.id.play_luck);

        final TextView playedLuckText = findViewById(R.id.played_luck_text);
        final Button playedLuckButton = findViewById(R.id.played_luck_button);

        playYourLuck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mLuckOfFate = mHeroDBHelper.isHeroLucky(mHeroID);

                if (mCurrentChapter == 159) {

                    if (mLuckOfFate) {

                        playYourLuck.setVisibility(View.GONE);
                        playedLuckButton.setVisibility(View.VISIBLE);

                        mLuckCurrent = mHeroDBHelper.currentLuck(mHeroID);
                        mCurrentChapter = 267;

                        if (mLuckCurrent == 0) {
                            playedLuckText.setText(getString(R.string.hero_luck_played_null));
                        } else {
                            playedLuckText.setText(String.format(getString(R.string.hero_luck_played),
                                    String.valueOf(mLuckCurrent)));
                        }

                        playedLuckButton.setText(getString(R.string.talisman_chapter203_choice1));

                        playedLuckButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                Intent intent = new Intent(PlayLuck.this, AdventureTalisman.class);
                                intent.putExtra("HeroID", mHeroID);
                                intent.putExtra("CurrentChapter", mCurrentChapter);
                                intent.putExtra("TotalChapters", mTotalChapters + 1);
                                startActivity(intent);

                            }
                        });


                    } else {

                        playYourLuck.setVisibility(View.GONE);
                        playedLuckButton.setVisibility(View.VISIBLE);

                        mLuckCurrent = mHeroDBHelper.currentLuck(mHeroID);
                        mCurrentChapter = 135;

                        if (mLuckCurrent == 0) {
                            playedLuckText.setText(getString(R.string.hero_luck_played_null));
                        } else {
                            playedLuckText.setText(String.format(getString(R.string.hero_luck_played),
                                    String.valueOf(mLuckCurrent)));
                        }

                        playedLuckButton.setText(getString(R.string.talisman_chapter203_choice2));

                        playedLuckButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                mAbilityLoss = 1;
                                mHeroDBHelper.currentAbilityLoss(mHeroID, mAbilityLoss);

                                Intent intent = new Intent(PlayLuck.this, AdventureTalisman.class);
                                intent.putExtra("HeroID", mHeroID);
                                intent.putExtra("CurrentChapter", mCurrentChapter);
                                intent.putExtra("TotalChapters", mTotalChapters + 1);
                                startActivity(intent);

                            }
                        });

                    }

                }

                if (mCurrentChapter == 203) {

                    if (mLuckOfFate) {

                        playYourLuck.setVisibility(View.GONE);
                        playedLuckButton.setVisibility(View.VISIBLE);

                        mLuckCurrent = mHeroDBHelper.currentLuck(mHeroID);
                        mCurrentChapter = 36;

                        if (mLuckCurrent == 0) {
                            playedLuckText.setText(getString(R.string.hero_luck_played_null));
                        } else {
                            playedLuckText.setText(String.format(getString(R.string.hero_luck_played),
                                    String.valueOf(mLuckCurrent)));
                        }

                        playedLuckButton.setText(getString(R.string.talisman_chapter203_choice1));

                        playedLuckButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                Intent intent = new Intent(PlayLuck.this, AdventureTalisman.class);
                                intent.putExtra("HeroID", mHeroID);
                                intent.putExtra("CurrentChapter", mCurrentChapter);
                                intent.putExtra("TotalChapters", mTotalChapters + 1);
                                startActivity(intent);

                            }
                        });


                    } else {

                        playYourLuck.setVisibility(View.GONE);
                        playedLuckButton.setVisibility(View.VISIBLE);

                        mLuckCurrent = mHeroDBHelper.currentLuck(mHeroID);
                        mCurrentChapter = 319;

                        if (mLuckCurrent == 0) {
                            playedLuckText.setText(getString(R.string.hero_luck_played_null));
                        } else {
                            playedLuckText.setText(String.format(getString(R.string.hero_luck_played),
                                    String.valueOf(mLuckCurrent)));
                        }

                        playedLuckButton.setText(getString(R.string.talisman_chapter203_choice2));

                        playedLuckButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                Intent intent = new Intent(PlayLuck.this, AdventureTalisman.class);
                                intent.putExtra("HeroID", mHeroID);
                                intent.putExtra("CurrentChapter", mCurrentChapter);
                                intent.putExtra("TotalChapters", mTotalChapters + 1);
                                startActivity(intent);

                            }
                        });

                    }

                }

            }
        });

    }

    // Back to adventure
    @Override
    public void onBackPressed() {

        Intent intent = new Intent(PlayLuck.this, AdventureTalisman.class);
        intent.putExtra("HeroID", String.valueOf(mHeroID));
        intent.putExtra("CurrentChapter", mCurrentChapter);
        intent.putExtra("TotalChapters", mTotalChapters);
        startActivity(intent);

    }

}
