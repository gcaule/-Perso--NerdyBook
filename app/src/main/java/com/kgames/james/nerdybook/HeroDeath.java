package com.kgames.james.nerdybook;

import android.content.Intent;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HeroDeath extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hero_death);

        final Button endAdventure = findViewById(R.id.hero_death_button);

        endAdventure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HeroDeath.this, MainMenu.class);
                startActivity(intent);
            }
        });

    }

    // Back to main menu
    @Override
    public void onBackPressed() {

        Intent intent = new Intent(HeroDeath.this, MainMenu.class);
        startActivity(intent);

    }

}
