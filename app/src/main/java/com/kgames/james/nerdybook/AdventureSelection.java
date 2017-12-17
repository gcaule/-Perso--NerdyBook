package com.kgames.james.nerdybook;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.kgames.james.nerdybook.Hero.DatabaseContract;
import com.kgames.james.nerdybook.Hero.HeroDBHelper;

import static com.kgames.james.nerdybook.Hero.DatabaseContract.HeroEntry.COLUMN_ADVENTURE;

public class AdventureSelection extends AppCompatActivity {

    HeroDBHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adventure_selection);

        final Button startTalisman = findViewById(R.id.start_talisman);
        final Button startMarks = findViewById(R.id.start_marks);

        startTalisman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mDbHelper = new HeroDBHelper(AdventureSelection.this);

                SQLiteDatabase database = mDbHelper.getWritableDatabase();
                ContentValues contentValues = new ContentValues();
                contentValues.put(COLUMN_ADVENTURE, String.valueOf(R.string.adventure_talisman_of_death));
                long newRowId = database.insert(DatabaseContract.HeroEntry.TABLE_NAME, null, contentValues);

                Intent intent = new Intent(AdventureSelection.this, HeroCreation.class);
                intent.putExtra("HeroID", newRowId);
                startActivity(intent);
            }
        });

        startMarks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(AdventureSelection.this, R.string.adventure_coming_soon, Toast.LENGTH_SHORT).show();
            }
        });

    }
}
