package com.kgames.james.nerdybook;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.kgames.james.nerdybook.Hero.DatabaseContract;
import com.kgames.james.nerdybook.Hero.HeroAdapter;
import com.kgames.james.nerdybook.Hero.HeroDBHelper;
import com.kgames.james.nerdybook.Hero.HeroModel;

import java.util.ArrayList;
import java.util.List;

public class SavedGames extends AppCompatActivity {

    List<HeroModel> mHeroList;
    ListView mListView;
    HeroDBHelper mDBHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_games);

        mDBHelper = new HeroDBHelper(SavedGames.this);

        mHeroList = new ArrayList<>();
        mListView = findViewById(R.id.saved_games_list);

        loadHeroesFromDatabase();
    }

    private void loadHeroesFromDatabase() {

        Cursor cursor = mDBHelper.getAllHeroes();

        if (cursor.moveToFirst()) {
            do {
                mHeroList.add(new HeroModel(
                        cursor.getString(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5),
                        cursor.getString(6),
                        cursor.getString(7),
                        cursor.getString(8),
                        cursor.getString(9),
                        cursor.getString(10),
                        cursor.getString(11),
                        cursor.getString(12)
                ));
            } while (cursor.moveToNext());

            HeroAdapter adapter = new HeroAdapter(this, R.layout.saved_list_row, mHeroList, mDBHelper);
            mListView.setAdapter(adapter);
        }
    }

}
