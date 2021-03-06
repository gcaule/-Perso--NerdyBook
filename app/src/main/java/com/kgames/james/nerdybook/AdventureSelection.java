package com.kgames.james.nerdybook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class AdventureSelection extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adventure_selection);

        final Button startTalisman = findViewById(R.id.start_talisman);
        final Button startMarks = findViewById(R.id.start_marks);

        startTalisman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(AdventureSelection.this, HeroCreation.class);
                intent.putExtra("AdventureTalisman", startTalisman.getText());
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
