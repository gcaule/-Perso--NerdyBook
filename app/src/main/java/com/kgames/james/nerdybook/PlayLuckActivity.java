package com.kgames.james.nerdybook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.kgames.james.nerdybook.Hero.HeroDBHelper;

public class PlayLuckActivity extends AppCompatActivity {

    HeroDBHelper mHeroDBHelper;

    String mHeroID;
    int mCurrentChapter;
    int mTotalChapters;
    int mLuckCurrent;

    boolean mLuckOfFate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_luck);

        mHeroDBHelper = new HeroDBHelper(PlayLuckActivity.this);

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

                                Intent intent = new Intent(PlayLuckActivity.this, AdventureTalisman.class);
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

                                Intent intent = new Intent(PlayLuckActivity.this, AdventureTalisman.class);
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
}
