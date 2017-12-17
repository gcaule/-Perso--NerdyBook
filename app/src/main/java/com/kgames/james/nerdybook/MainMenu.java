package com.kgames.james.nerdybook;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.kgames.james.nerdybook.Hero.HeroDBHelper;

import java.io.File;

import static com.kgames.james.nerdybook.Hero.DatabaseContract.HeroEntry.TABLE_NAME;

public class MainMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__main_menu);

        SQLiteDatabase db = new HeroDBHelper(MainMenu.this).getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        final Boolean tableIsEmpty;
        if(cursor.getCount() == 0)  {
            tableIsEmpty = true;
        } else {
            tableIsEmpty = false;
        }

        Button newAdventure = findViewById(R.id.start_adventure);
        Button continueAdventure = findViewById(R.id.continue_adventure);

        newAdventure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainMenu.this, AdventureSelection.class);
                startActivity(intent);
            }
        });

        continueAdventure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!doesDatabaseExist(getApplicationContext(), "Heroes.db") || tableIsEmpty) {
                    Toast.makeText(MainMenu.this, R.string.no_saved_games, Toast.LENGTH_SHORT).show();

                } else {
                    Intent intent = new Intent(MainMenu.this, SavedGames.class);
                    startActivity(intent);
                }
            }
        });

    }

    private static boolean doesDatabaseExist(Context context, String dbName) {
        File dbFile = context.getDatabasePath(dbName);
        return dbFile.exists();
    }

}
