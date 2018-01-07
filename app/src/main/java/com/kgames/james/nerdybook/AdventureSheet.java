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
import com.kgames.james.nerdybook.Hero.HeroModel;

import static android.view.View.GONE;

public class AdventureSheet extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    Toolbar mToolbar;
    HeroDBHelper mHeroDBHelper;

    String mHeroID;
    int mCurrentChapter;
    int mTotalChapters;

    HeroModel mCurrentHero;

    HeroDBHelper mDBHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adventure_sheet);

        mHeroID = getIntent().getExtras().getString("HeroID");
        mCurrentChapter = getIntent().getExtras().getInt("CurrentChapter");
        mTotalChapters = getIntent().getExtras().getInt("TotalChapters");


        TextView adventureName = findViewById(R.id.adventure_name);
        TextView heroName = findViewById(R.id.hero_name);

        final TextView abilityValues = findViewById(R.id.ability_value);
        final TextView staminaValues = findViewById(R.id.stamina_value);
        final TextView luckValues = findViewById(R.id.luck_value);

        TextView stuff1 = findViewById(R.id.stuff1);
        TextView stuff2 = findViewById(R.id.stuff2);
        TextView stuff3 = findViewById(R.id.stuff3);

        TextView goldenCoins = findViewById(R.id.golden_coins_value);
        TextView torchs = findViewById(R.id.torchs_value);
        TextView meals = findViewById(R.id.meals_value);

        TextView abilityPotionsText = findViewById(R.id.ability_potions);
        TextView staminaPotionsText = findViewById(R.id.stamina_potions);
        TextView luckPotionsText = findViewById(R.id.luck_potions);

        final TextView abilityPotions = findViewById(R.id.ability_potions_value);
        final TextView staminaPotions = findViewById(R.id.stamina_potions_value);
        final TextView luckPotions = findViewById(R.id.luck_potions_value);

        final Button abilityPotionUse = findViewById(R.id.consume_ability_potion);
        final Button staminaPotionUse = findViewById(R.id.consume_stamina_potion);
        final Button luckPotionUse = findViewById(R.id.consume_luck_potion);


        mHeroDBHelper = new HeroDBHelper(AdventureSheet.this);
        mCurrentHero = mHeroDBHelper.getCurrentHero(mHeroID);

        adventureName.setText(mCurrentHero.getAdventure());
        heroName.setText(mCurrentHero.getName());

        abilityValues.setText(String.format(getString(R.string.ability_value),
                String.valueOf(mCurrentHero.getAbilityCurrent()), String.valueOf(mCurrentHero.getAbilityMax())));

        staminaValues.setText(String.format(getString(R.string.stamina_value),
                String.valueOf(mCurrentHero.getStaminaCurrent()), String.valueOf(mCurrentHero.getStaminaMax())));

        luckValues.setText(String.format(getString(R.string.luck_value),
                String.valueOf(mCurrentHero.getLuckCurrent()), String.valueOf(mCurrentHero.getLuckMax())));

        stuff1.setText(mCurrentHero.getStuff1());
        stuff2.setText(mCurrentHero.getStuff2());
        stuff3.setText(mCurrentHero.getStuff3());

        goldenCoins.setText(String.valueOf(mCurrentHero.getGoldenCoins()));
        torchs.setText(String.valueOf((mCurrentHero.getTorchs())));
        meals.setText(String.valueOf(mCurrentHero.getMeals()));

        abilityPotions.setText(String.valueOf(mCurrentHero.getAbilityPotions()));
        staminaPotions.setText(String.valueOf(mCurrentHero.getStaminaPotions()));
        luckPotions.setText(String.valueOf(mCurrentHero.getLuckPotions()));


        if (mCurrentHero.getStuff1().isEmpty()) {
            stuff1.setVisibility(GONE);
        }

        if (mCurrentHero.getStuff2().isEmpty()) {
            stuff2.setVisibility(GONE);
        }

        if (mCurrentHero.getStuff3().isEmpty()) {
            stuff3.setVisibility(GONE);
        }


        if (mCurrentHero.getAbilityPotions() != 0) {
            abilityPotionsText.setVisibility(View.VISIBLE);
            abilityPotions.setVisibility(View.VISIBLE);
            abilityPotionUse.setVisibility(View.VISIBLE);

            abilityPotionUse.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    mHeroDBHelper.useAbilityPotion(mHeroID);

                    mHeroDBHelper = new HeroDBHelper(AdventureSheet.this);
                    mCurrentHero = mHeroDBHelper.getCurrentHero(mHeroID);

                    abilityValues.setText(String.format(getString(R.string.ability_value),
                            String.valueOf(mCurrentHero.getAbilityCurrent()), String.valueOf(mCurrentHero.getAbilityMax())));

                    abilityPotions.setText(String.valueOf(mCurrentHero.getAbilityPotions()));

                    if (mHeroDBHelper.currentAbilityPotions(mHeroID) == 0)
                        abilityPotionUse.setEnabled(false);
                }
            });
        }

        if (mCurrentHero.getStaminaPotions() != 0) {
            staminaPotionsText.setVisibility(View.VISIBLE);
            staminaPotions.setVisibility(View.VISIBLE);
            staminaPotionUse.setVisibility(View.VISIBLE);

            staminaPotionUse.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    mHeroDBHelper.useStaminaPotion(mHeroID);

                    mHeroDBHelper = new HeroDBHelper(AdventureSheet.this);
                    mCurrentHero = mHeroDBHelper.getCurrentHero(mHeroID);

                    staminaValues.setText(String.format(getString(R.string.stamina_value),
                            String.valueOf(mCurrentHero.getStaminaCurrent()), String.valueOf(mCurrentHero.getStaminaMax())));

                    staminaPotions.setText(String.valueOf(mCurrentHero.getStaminaPotions()));

                    if (mHeroDBHelper.currentStaminaPotions(mHeroID) == 0)
                        staminaPotionUse.setEnabled(false);
                }
            });
        }

        if (mCurrentHero.getLuckPotions() != 0) {
            luckPotionsText.setVisibility(View.VISIBLE);
            luckPotions.setVisibility(View.VISIBLE);
            luckPotionUse.setVisibility(View.VISIBLE);

            abilityPotionUse.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    mHeroDBHelper.useLuckPotion(mHeroID);

                    mHeroDBHelper = new HeroDBHelper(AdventureSheet.this);
                    mCurrentHero = mHeroDBHelper.getCurrentHero(mHeroID);

                    luckValues.setText(String.format(getString(R.string.luck_value),
                            String.valueOf(mCurrentHero.getLuckCurrent()), String.valueOf(mCurrentHero.getLuckMax())));

                    luckPotions.setText(String.valueOf(mCurrentHero.getLuckPotions()));

                    if (mHeroDBHelper.currentLuckPotions(mHeroID) == 0)
                        luckPotionUse.setEnabled(false);
                }
            });
        }


        // Toolbar
        mToolbar = findViewById(R.id.toolbar);
        mToolbar.setTitle(getResources().getString(R.string.nav_adventure_sheet));
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
        nav_Menu.findItem(R.id.nav_adventure_sheet).setVisible(false);
        View headerview = navigationView.getHeaderView(0);
        headerview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), AdventureTalisman.class));
            }
        });

    }


    // Drawer Menu - back to adventure
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            Intent intent = new Intent(AdventureSheet.this, AdventureTalisman.class);
            intent.putExtra("HeroID", String.valueOf(mHeroID));
            intent.putExtra("CurrentChapter", mCurrentChapter);
            intent.putExtra("TotalChapters", mTotalChapters);
            startActivity(intent);
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
            Intent intent = new Intent(getApplicationContext(), AdventureSheet.class);
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
